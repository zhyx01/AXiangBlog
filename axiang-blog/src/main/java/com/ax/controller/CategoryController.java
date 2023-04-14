package com.ax.controller;

import com.ax.domain.ResponseResult;
import com.ax.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * className: CategoryController
 * description: 文章列表分类
 *
 * @author: axiang
 * date: 2023/4/9 13:01
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * description: 查看分类列表
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @GetMapping("getCategoryList")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }
}
