package com.ax.mapper;

import com.ax.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-09 16:24:36
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
