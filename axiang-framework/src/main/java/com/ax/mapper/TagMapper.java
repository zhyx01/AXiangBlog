package com.ax.mapper;

import com.ax.domain.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 标签(Tag)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-11 13:56:30
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
