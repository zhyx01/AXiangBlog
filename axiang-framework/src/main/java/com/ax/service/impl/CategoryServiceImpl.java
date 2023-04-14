package com.ax.service.impl;

import com.ax.constans.SystemConstants;
import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Article;
import com.ax.domain.entity.Category;
import com.ax.domain.vo.CategoryVo;
import com.ax.mapper.CategoryMapper;
import com.ax.service.ArticleService;
import com.ax.service.CategoryService;
import com.ax.utils.BeanCopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-04-09 12:40:47
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {

        // 查询文章表, 状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(articleWrapper);

        // 获取文章的分类id, 并且去重
        Set<Long> categoryIds = articles.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        // 查询分类表
        List<Category> categories = listByIds(categoryIds);
        // 状态必须是正常的分类
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Category::getStatus, SystemConstants.CATEGORIES_STATUS_NORMAL);
        categories = list(queryWrapper);

        // 封装vo
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(categories, CategoryVo.class);

        // 返回
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> getAllCategoryList() {

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件, 状态为正常的
        queryWrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(queryWrapper);
        // 封装成vo
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(list, CategoryVo.class);
        // 返回
        return categoryVos;
    }
}
