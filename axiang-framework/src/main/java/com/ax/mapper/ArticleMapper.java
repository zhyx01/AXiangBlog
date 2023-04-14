package com.ax.mapper;

import com.ax.domain.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * className: ArticleMapper
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 9:42
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
