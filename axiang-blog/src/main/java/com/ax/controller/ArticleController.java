package com.ax.controller;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Article;
import com.ax.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * className: ArticleController
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 9:44
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * description: 测试接口
     * @param :
     * @return: String <br>
     * date: 2023/4/10 0010 <br>
     */
    @GetMapping("/list")
    public String test() {
        List<Article> list = articleService.list();
        System.out.println(list);
        return "ok";
    }

    /**
     * description: 浏览量前10
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/9 <br>
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    /**
     * description: 分页查文章列表
     * @param categoryId:
     * @param pageNum:
     * @param pageSize:
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @GetMapping("/articleList")
    public ResponseResult getArticleList(Long categoryId, Integer pageNum, Integer pageSize) {
        return articleService.getArticleList(categoryId, pageNum, pageSize);
    }

    /**
     * description: 查询文章详情
     * @param id: 文章id
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @GetMapping("/{id}")
    public ResponseResult articleDetail(@PathVariable Long id) {
        return articleService.getArticleDetail(id);
    }

    /**
     * description: 添加文章浏览量
     * @param id: 文章id
     * @return: ResponseResult <br>
     * date: 2023/4/11 0011 <br>
     */
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}
