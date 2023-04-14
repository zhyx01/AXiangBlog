package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.domain.entity.LoginUser;
import com.ax.domain.entity.Menu;
import com.ax.domain.entity.User;
import com.ax.domain.vo.AdminUserInfoVo;
import com.ax.domain.vo.RoutersVo;
import com.ax.domain.vo.UserInfoVo;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import com.ax.service.AdminLoginService;
import com.ax.service.MenuService;
import com.ax.service.RoleService;
import com.ax.utils.BeanCopyUtil;
import com.ax.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * className: AdminUserController
 * description: 博客后台登录
 *
 * @author: axiang
 * date: 2023/4/11 0011 14:28
 */
@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;


    /**
     * description: 后台登录
     * @param user: 封装账号密码
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @PostMapping("/user/login")
    @SystemLog(businessName = "后台登录")
    public ResponseResult login(@RequestBody User user){

        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    /**
     * description: 后台退出
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @PostMapping("/user/logout")
    @SystemLog(businessName = "后台退出")
    public ResponseResult logout(){

        return adminLoginService.logout();
    }

    /**
     * description: 查询用户的权限以及角色信息
     * @param :
     * @return: ResponseResult<AdminUserInfoVo> <br>
     * date: 2023/4/12 0012 <br>
     */
    @GetMapping("getInfo")
    @SystemLog(businessName = "获取用户权限和角色信息")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtil.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(user, UserInfoVo.class);
        //封装数据返回

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }


    /**
     * description: 根据用户id查询菜单
     * @param :
     * @return: ResponseResult<RoutersVo> <br>
     * date: 2023/4/12 0012 <br>
     */
    @GetMapping("getRouters")
    @SystemLog(businessName = "获取动态路由")
    public ResponseResult<RoutersVo> getRouters(){

        Long userId = SecurityUtil.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
