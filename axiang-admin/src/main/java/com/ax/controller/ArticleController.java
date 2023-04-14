package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.domain.dto.AddArticleDto;
import com.ax.domain.dto.ArticleListDto;
import com.ax.domain.entity.Article;
import com.ax.service.ArticleService;
import com.ax.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * className: ArticleController
 * description: 写博文
 *
 * @author: axiang
 * date: 2023/4/12 0012 18:40
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @SystemLog(businessName = "写博文")
    public ResponseResult addArticle(@RequestBody AddArticleDto addArticleDto) {
        return articleService.add(addArticleDto);
    }

    /**
     * description: 查询文章列表(博客后台)
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/14 0014 <br>
     */
    @GetMapping("/list")
    @SystemLog(businessName = "博客后台: 文章列表")
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto) {
        return articleService.articleList(pageNum, pageSize, articleListDto);
    }
}
