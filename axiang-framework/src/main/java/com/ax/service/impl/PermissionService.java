package com.ax.service.impl;

import com.ax.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * className: PermissionService
 * description:
 *
 * @author: axiang
 * date: 2023/4/12 0012 21:12
 */
@Service("ps")
public class PermissionService {

    /**
     * description: 判断用户是否具有操作权限
     * @param permission: 要判断的权限
     * @return: boolean <br> 返回是否可以操作
     * date: 2023/4/12 0012 <br>
     */
    public boolean hasPermission(String permission) {
        // 如果是超级管理员, 直接返回true
        if (SecurityUtil.isAdmin()) {
            return true;
        }
        // 否则, 获取当前用户所具有的权限列表, 判断是否存在permission
        List<String> permissions = SecurityUtil.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
