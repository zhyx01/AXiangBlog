package com.ax.handler.exception;

import com.ax.domain.ResponseResult;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * className: GlobalExceptionHandler
 * description: controller层全局异常处理
 *
 * @author: axiang
 * date: 2023/4/9 23:12
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {

        // 使用日志打印异常信息
        log.error("出现了异常 {}", e);

        // 从异常对象中获取异常信息封装单结果集返回
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {

        // 使用日志打印异常信息
        log.error("出现了异常 {}", e);

        // 从异常对象中获取异常信息封装单结果集返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
