package com.ax.controller;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.User;
import com.ax.service.BlogLoginService;
import kotlin.UByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * className: BlogLoginController
 * description: 登录接口
 *
 * @author: axiang
 * date: 2023/4/9 16:56
 */
@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    /**
     * description: 登录
     * @param user: 封装账号和密码
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return blogLoginService.login(user);
    }

    /**
     * description: 退出
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @PostMapping("/logout")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }
}
