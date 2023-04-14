package com.ax.mapper;

import com.ax.domain.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-09 15:42:30
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}
