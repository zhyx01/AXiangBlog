package com.ax.mapper;

import com.ax.domain.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-12 18:48:42
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}
