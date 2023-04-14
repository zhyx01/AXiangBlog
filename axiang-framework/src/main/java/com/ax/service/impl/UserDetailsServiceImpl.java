package com.ax.service.impl;

import com.ax.constans.SystemConstants;
import com.ax.domain.entity.LoginUser;
import com.ax.domain.entity.User;
import com.ax.mapper.MenuMapper;
import com.ax.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * className: UserDetailsServiceImpl
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 18:42
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);

        // 判断是否查到用户, 没有则抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }

        // 返回用户信息
        if (user.getType().equals(SystemConstants.ADMIN)) {
            List<String> list = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user, list);
        }
        return new LoginUser(user, null);
    }
}
