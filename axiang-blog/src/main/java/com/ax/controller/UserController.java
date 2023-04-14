package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.domain.entity.User;
import com.ax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * className: UserController
 * description:
 *
 * @author: axiang
 * date: 2023/4/10 0010 15:28
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * description: 个人中心
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        return userService.userInfo();
    }

    /**
     * description: 修改用户个人资料
     * @param user: 用户信息
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @PutMapping("/userInfo")
    @SystemLog(businessName = "修改用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    /**
     * description: 用户注册
     * @param user: 用户信息
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }
}
