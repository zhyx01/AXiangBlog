package com.ax.service;

import com.ax.domain.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;


public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenuByRoleId(Long id);
}