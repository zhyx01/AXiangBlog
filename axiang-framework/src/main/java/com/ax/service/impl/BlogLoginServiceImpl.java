package com.ax.service.impl;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.LoginUser;
import com.ax.domain.entity.User;
import com.ax.domain.vo.BlogUserLoginVo;
import com.ax.domain.vo.UserInfoVo;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import com.ax.service.BlogLoginService;
import com.ax.utils.BeanCopyUtil;
import com.ax.utils.JwtUtil;
import com.ax.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * className: BlogLoginServiceImpl
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 16:58
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        // 使用AuthenticationManager进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证失败,给出对应的提示(判断authenticate是否为空, 为空则认证失败)
        if (Objects.isNull(authenticate)) {
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }

        // 认证通过, 使用userid生成一个jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        // 将完整的用户信息存入redis
        redisCache.setCacheObject("blogLogin:" + userId, loginUser);

        // 把User转成UserinfoVo
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);
        // 把token和userinfo封装 返回给前端
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);

        // 返回
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        // 获取token, 解析token, 获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        // 从redis中删除
        redisCache.deleteObject("blogLogin:" + userId);

        // 返回
        return ResponseResult.okResult();
    }
}
