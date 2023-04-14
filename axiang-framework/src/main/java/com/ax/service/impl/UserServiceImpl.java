package com.ax.service.impl;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.User;
import com.ax.domain.vo.UserInfoVo;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import com.ax.mapper.UserMapper;
import com.ax.service.UserService;
import com.ax.utils.BeanCopyUtil;
import com.ax.utils.SecurityUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-04-10 09:54:26
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult userInfo() {

        // 获取token, 拿到userId
        Long userId = SecurityUtil.getUserId();

        // 根据userId获取用户信息
        User user = getById(userId);

        // 封装成userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(user, UserInfoVo.class);

        // 返回
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        // 对数据进行非空判断
        // 用户名
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        // 密码
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        // 昵称
        if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        // 邮箱
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }

        // 对数据进行数据库是否已经存在判断
        // 用户名
        if (userNameExit(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        // 昵称
        if (nickNameExit(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        // 邮箱
        if (emailExit(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        // 存入数据库
        save(user);

        // 返回
        return ResponseResult.okResult();
    }

    /**
     * description: 判断用户名是否已存在
     * @param userName:
     * @return: boolean <br>
     * date: 2023/4/10 0010 <br>
     */
    private boolean userNameExit(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        return count(queryWrapper) > 0;
    }

    /**
     * description: 判断昵称是否已存在
     * @param nickNameExit:
     * @return: boolean <br>
     * date: 2023/4/10 0010 <br>
     */
    private boolean nickNameExit(String nickNameExit) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName, nickNameExit);
        return count(queryWrapper) > 0;
    }

    /**
     * description: 判断邮箱是否已存在
     * @param emailExit:
     * @return: boolean <br>
     * date: 2023/4/10 0010 <br>
     */
    private boolean emailExit(String emailExit) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, emailExit);
        return count(queryWrapper) > 0;
    }
}
