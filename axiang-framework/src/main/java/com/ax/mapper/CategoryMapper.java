package com.ax.mapper;

import com.ax.domain.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-09 12:40:44
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
