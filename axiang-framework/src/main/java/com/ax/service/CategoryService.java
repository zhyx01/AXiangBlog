package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Category;
import com.ax.domain.vo.CategoryVo;
import com.ax.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-04-09 12:40:47
 */
public interface CategoryService extends IService<Category> {

    /**
     * description: 获取文章分类列表, 前台
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    ResponseResult getCategoryList();

    /**
     * description: 写博文下拉框, 后台获取文章分类列表
     * @param :
     * @return: List<CategoryVo> <br>
     * date: 2023/4/12 0012 <br>
     */
    List<CategoryVo> getAllCategoryList();

    /**
     * description: 分页查询分类列表
     * @param category: 封装条件
     * @param pageNum: 页码
     * @param pageSize: 每页显示大小
     * @return: PageVo <br>
     * date: 2023/4/16 0016 <br>
     */
    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}
