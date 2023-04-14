package com.ax.mapper;

import com.ax.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-11 19:32:24
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * description: 根据用户id查询角色信息
     * @param id: 用户id
     * @return: List<String> <br>
     * date: 2023/4/11 0011 <br>
     */
    List<String> selectRoleKeyByUserId(Long id);
}
