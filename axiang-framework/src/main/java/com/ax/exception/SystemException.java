package com.ax.exception;

import com.ax.enums.AppHttpCodeEnum;

/**
 * className: SystemException
 * description: 统一异常处理
 *
 * @author: axiang
 * date: 2023/4/9 23:10
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
