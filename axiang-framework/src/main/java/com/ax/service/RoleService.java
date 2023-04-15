package com.ax.service;

import com.ax.domain.ResponseResult;
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

    /**
     * description: 分页查角色信息
     * @param role: 查询条件(允许为空)
     * @param pageNum: 当前页码
     * @param pageSize: 每页显示大小
     * @return: ResponseResult <br>
     * date: 2023/4/15 0015 <br>
     */
    ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize);
}
