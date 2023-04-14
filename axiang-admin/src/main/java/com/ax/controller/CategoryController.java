package com.ax.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Category;
import com.ax.domain.vo.CategoryVo;
import com.ax.domain.vo.ExcelCategoryVo;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.service.CategoryService;
import com.ax.utils.BeanCopyUtil;
import com.ax.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * className: CategoryController
 * description:
 *
 * @author: axiang
 * date: 2023/4/12 0012 15:25
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * description: 写博文, 查询所有分类列表, 写博文下拉框选择该篇博文的分类, 单选
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @GetMapping("/listAllCategory")
    @SystemLog(businessName = "查询写博文下拉框的分类标签")
    public ResponseResult getAllCategoryList() {

        List<CategoryVo> list = categoryService.getAllCategoryList();
        return ResponseResult.okResult(list);
    }

    @GetMapping("/export")
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @SystemLog(businessName = "导出分类列表")
    public void export(HttpServletResponse response) {
        try {
            // 设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            // 获取需要导出的数据
            List<Category> categories = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtil.copyBeanList(categories, ExcelCategoryVo.class);
            // 吧数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);
        } catch (Exception e) {
            // 如果出现异常, 也要相应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            // 重置
            response.reset();
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
}
