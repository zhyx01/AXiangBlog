package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Role;
import com.ax.domain.entity.User;
import com.ax.domain.vo.UserInfoAndRoleIdsVo;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import com.ax.service.RoleService;
import com.ax.service.UserService;
import com.ax.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    @SystemLog(businessName = "获取用户列表")
    public ResponseResult list(User user, Integer pageNum, Integer pageSize) {
        return userService.selectUserPage(user ,pageNum, pageSize);
    }

    /**
     * 新增用户
     */
    @PostMapping
    @SystemLog(businessName = "新增用户")
    public ResponseResult add(@RequestBody User user)
    {
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        if (!userService.checkUserNameUnique(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (!userService.checkPhoneUnique(user)){
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if (!userService.checkEmailUnique(user)){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        return userService.addUser(user);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping(value = { "/{userId}" })
    @SystemLog(businessName = "根据用户编号获取详细信息")
    public ResponseResult getUserInfoAndRoleIds(@PathVariable(value = "userId") Long userId)
    {
        List<Role> roles = roleService.selectRoleAll();
        User user = userService.getById(userId);
        //当前用户所具有的角色id列表
        List<Long> roleIds = roleService.selectRoleIdByUserId(userId);

        UserInfoAndRoleIdsVo vo = new UserInfoAndRoleIdsVo(user,roles,roleIds);
        return ResponseResult.okResult(vo);
    }

    /**
     * 修改用户
     */
    @PutMapping
    @SystemLog(businessName = "修改用户")
    public ResponseResult edit(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseResult.okResult();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userIds}")
    @SystemLog(businessName = "删除用户")
    public ResponseResult remove(@PathVariable List<Long> userIds) {
        if(userIds.contains(SecurityUtil.getUserId())){
            return ResponseResult.errorResult(500,"不能删除当前你正在使用的用户");
        }
        userService.removeByIds(userIds);
        return ResponseResult.okResult();
    }
}