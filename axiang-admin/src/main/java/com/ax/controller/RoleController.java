package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.domain.dto.ChangeRoleStatusDto;
import com.ax.domain.entity.Role;
import com.ax.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * className: RoleController
 * description: 角色管理
 *
 * @author: axiang
 * date: 2023/4/15 0015 23:13
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @SystemLog(businessName = "角色管理列表")
    public ResponseResult list(Role role, Integer pageNum, Integer pageSize) {
        return roleService.selectRolePage(role, pageNum, pageSize);
    }

    @PutMapping("/changeStatus")
    @SystemLog(businessName = "修改角色状态")
    public ResponseResult changeStatus(@RequestBody ChangeRoleStatusDto roleStatusDto) {
        Role role = new Role();
        role.setId(roleStatusDto.getRoleId());
        role.setStatus(roleStatusDto.getStatus());
        return ResponseResult.okResult(roleService.updateById(role));
    }

    /**
     * 新增角色
     */
    @PostMapping
    @SystemLog(businessName = "新增角色")
    public ResponseResult add(@RequestBody Role role) {
        roleService.insertRole(role);
        return ResponseResult.okResult();

    }

    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    @SystemLog(businessName = "根据角色编号获取详细信息(修改角色信息)")
    public ResponseResult getInfo(@PathVariable Long roleId) {
        Role role = roleService.getById(roleId);
        return ResponseResult.okResult(role);
    }

    /**
     * 修改保存角色
     */
    @PutMapping
    @SystemLog(businessName = "更新角色信息接口")
    public ResponseResult edit(@RequestBody Role role) {
        roleService.updateRole(role);
        return ResponseResult.okResult();
    }

    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除角色")
    public ResponseResult remove(@PathVariable(name = "id") Long id) {
        roleService.removeById(id);
        return ResponseResult.okResult();
    }
}
