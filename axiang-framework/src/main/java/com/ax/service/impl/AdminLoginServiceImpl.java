package com.ax.service.impl;

import com.ax.constans.SystemConstants;
import com.ax.domain.ResponseResult;
import com.ax.domain.entity.LoginUser;
import com.ax.domain.entity.User;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import com.ax.service.AdminLoginService;
import com.ax.utils.JwtUtil;
import com.ax.utils.RedisCache;
import com.ax.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * className: AdminLoginServiceImpl
 * description:
 *
 * @author: axiang
 * date: 2023/4/11 0011 14:33
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // 使用AuthenticationManager进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证失败,给出对应的提示(判断authenticate是否为空, 为空则认证失败)
        if (Objects.isNull(authenticate)) {
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        // 认证通过, 使用userid生成一个jwt
        // 1. 获取userId
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(userId.toString());

        // 将完整的用户信息存入redis
        redisCache.setCacheObject("adminLogin:" + userId, loginUser);

        // 把token封装返回给前端
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //返回
        return ResponseResult.okResult(map);
    }

/*    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        //把token封装 返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }*/

    @Override
    public ResponseResult logout() {
        //获取当前登录的用户id
        Long userId = SecurityUtil.getUserId();
        //删除redis中对应的值
        redisCache.deleteObject("adminLogin:"+userId);
        return ResponseResult.okResult();
    }
}
