package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.User;

public interface BlogLoginService {
    /**
     * description: 登录
     * @param user: 接收账号密码
     * @return: ResponseResult <br>
     * date: 2023/4/9 <br>
     */
    ResponseResult login(User user);

    /**
     * description: 退出登录
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/9 <br>
     */
    ResponseResult logout();

}
