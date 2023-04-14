package com.ax.handler.security;

import com.alibaba.fastjson.JSON;
import com.ax.domain.ResponseResult;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * className: AccessDeniedHandlerImpl
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 22:54
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 打印异常信息
        accessDeniedException.printStackTrace();

        accessDeniedException.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
