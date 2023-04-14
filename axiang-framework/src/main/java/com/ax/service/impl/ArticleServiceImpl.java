package com.ax.service.impl;

import com.ax.annotation.SystemLog;
import com.ax.constans.SystemConstants;
import com.ax.domain.ResponseResult;
import com.ax.domain.dto.AddArticleDto;
import com.ax.domain.dto.ArticleListDto;
import com.ax.domain.entity.Article;
import com.ax.domain.entity.ArticleTag;
import com.ax.domain.entity.Category;
import com.ax.domain.vo.*;
import com.ax.mapper.ArticleMapper;
import com.ax.service.ArticleService;
import com.ax.service.ArticleTagService;
import com.ax.service.CategoryService;
import com.ax.utils.BeanCopyUtil;
import com.ax.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.xml.bind.attachment.AttachmentMarshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * className: ArticleServiceImpl
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 9:41
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public ResponseResult hotArticleList() {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        // 1. 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        // 2. 按照浏览量进行降序排序
        queryWrapper.orderByDesc(Article::getViewCount);

        // 3. 最多只查询10条
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

        // 查询, 将浏览量使用redis中的数据显示, 后续通过定时任务存入数据库
        for (Article article : articles) {
            Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT, article.getId().toString());
            article.setViewCount(Long.valueOf(viewCount));
        }

        // bean拷贝
        /*List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article, vo);
            articleVos.add(vo);
        }*/

        // 优化
        List<HotArticleVo> articleVos = BeanCopyUtil.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);

    }

    @Override
    @SystemLog(businessName = "查询文章列表")
    public ResponseResult getArticleList(Long categoryId, Integer pageNum, Integer pageSize) {

        // 查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();

        // 如果有categoryId, 需要 查询时要和传入的相同
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0,
                        Article::getCategoryId, categoryId);

        // 状态是正式发布的
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        // 对istop进行排序
        queryWrapper.orderByDesc(Article::getIsTop);

        // 分页查询
        Page<Article> page = new Page(pageNum, pageSize);
        page(page, queryWrapper);

        // 查询categoryName
        List<Article> articles = page.getRecords();
        // 根据这里的articleId去查询articleName进行设置
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }

        // 查询, 将浏览量使用redis中的数据显示, 后续通过定时任务存入数据库
        for (Article article : articles) {
            Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT, article.getId().toString());
            article.setViewCount(Long.valueOf(viewCount));
        }

        // 封装vo
        List<ArticleListVo> articleVos = BeanCopyUtil.copyBeanList(page.getRecords(), ArticleListVo.class);

        // 封装分页vo
        PageVo pageVo = new PageVo(articleVos, page.getTotal());

        // 返回
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {

        // 查询
        Article article = getById(id);

        // 获取redis中的浏览量展示
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT, id.toString());
        article.setViewCount(Long.valueOf(viewCount));

        // 封装vo
        ArticleDetailVo articleDetailVo = BeanCopyUtil.copyBean(article, ArticleDetailVo.class);

        // 根据id查询分类名
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        articleDetailVo.setCategoryName(category.getName());

        // 封装相应结果返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {

        // 更新redis中存储的对应文章的浏览量 每次加一
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT, id.toString(), 1);

        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto addArticleDto) {
        // 两次添加必须同时成功, 同时失败 ==> 开启事务

        // 添加 博客
        Article article = BeanCopyUtil.copyBean(addArticleDto, Article.class);
        save(article);

        List<ArticleTag> articleTags = addArticleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        // 添加 博客和标签之间的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto) {

        // 查询所有
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 条件
        queryWrapper.eq(StringUtils.hasText(articleListDto.getTitle()), Article::getTitle, articleListDto.getTitle());
        queryWrapper.eq(StringUtils.hasText(articleListDto.getSummary()), Article::getSummary, articleListDto.getSummary());
        //queryWrapper.eq(Article::getStatus, SystemConstants.NORMAL);

        // 分页
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        // 封装
        List<AdminArticleListVo> adminArticleListVos = BeanCopyUtil.copyBeanList(page.getRecords(), AdminArticleListVo.class);
        PageVo pageVo = new PageVo(adminArticleListVos, page.getTotal());

        // 返回
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult updateArticle(UpdateArticleVo articleVo) {

        Article article = BeanCopyUtil.copyBean(articleVo, Article.class);
        updateById(article);
        return ResponseResult.okResult();
    }
}
