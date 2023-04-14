package com.ax.controller;

import com.ax.domain.ResponseResult;
import com.ax.domain.dto.AddArticleDto;
import com.ax.domain.entity.Article;
import com.ax.service.ArticleService;
import com.ax.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * className: ArticleController
 * description: 写博文
 *
 * @author: axiang
 * date: 2023/4/12 0012 18:40
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/content/article")
    public ResponseResult addArticle(@RequestBody AddArticleDto addArticleDto) {
        return articleService.add(addArticleDto);
    }
}
