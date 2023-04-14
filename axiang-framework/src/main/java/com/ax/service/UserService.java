package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-04-10 09:54:25
 */
public interface UserService extends IService<User> {

    /**
     * description: 个人中心
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    ResponseResult userInfo();

    /**
     * description: 修改用户个人信息
     * @param user: 个人信息
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    ResponseResult updateUserInfo(User user);

    /**
     * description: 用户注册
     * @param user: 用户信息
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    ResponseResult register(User user);
}
