package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.User;

public interface AdminLoginService {

    /**
     * description: 博客后台登录功能
     * @param user: 封装账号密码
     * @return: ResponseResult <br>
     * date: 2023/4/11 0011 <br>
     */
    ResponseResult login(User user);

    /**
     * description: 用户后台退出
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    ResponseResult logout();
}
