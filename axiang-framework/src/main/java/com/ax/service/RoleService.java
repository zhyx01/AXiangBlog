package com.ax.service;

import com.ax.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-04-11 19:32:25
 */
public interface RoleService extends IService<Role> {

    /**
     * description: 根据用户id查询角色信息
     * @param id: 用户id
     * @return: List<String> <br>
     * date: 2023/4/11 0011 <br>
     */
    List<String> selectRoleKeyByUserId(Long id);
}
