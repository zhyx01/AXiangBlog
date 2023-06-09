package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.dto.AddArticleDto;
import com.ax.domain.dto.ArticleListDto;
import com.ax.domain.entity.Article;
import com.ax.domain.vo.UpdateArticleVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleService extends IService<Article> {

    /**
     * description: 热门文章排行
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/9 <br>
     */
    ResponseResult hotArticleList();

    /**
     * description: 文章列表
     * @param categoryId: 分类id
     * @param pageNum: 页码
     * @param pageSize: 大小
     * @return: ResponseResult <br>
     * date: 2023/4/9 <br>
     */
    ResponseResult getArticleList(Long categoryId, Integer pageNum, Integer pageSize);

    /**
     * description: 查询文章详细信息
     * @param id: 文章id
     * @return: ResponseResult <br>
     * date: 2023/4/9 <br>
     */
    ResponseResult getArticleDetail(Long id);

    /**
     * description: 增加浏览量
     * @param id: 文章id
     * @return: ResponseResult <br>
     * date: 2023/4/11 0011 <br>
     */
    ResponseResult updateViewCount(Long id);

    /**
     * description: 添加博文
     * @param addArticleDto: 博文内容
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    ResponseResult add(AddArticleDto addArticleDto);

    /**
     * description: 博客后台: 查看文章列表
     * @param pageNum: 页码
     * @param pageSize: 显示大小
     * @param articleListDto: 条件
     * @return: ResponseResult <br>
     * date: 2023/4/14 0014 <br>
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto);

    /**
     * description: 修改文章内容
     * @param articleVo: 文章内容
     * @return: ResponseResult <br>
     * date: 2023/4/14 0014 <br>
     */
    ResponseResult updateArticle(UpdateArticleVo articleVo);
}
