package com.ax.aspect;

import com.alibaba.fastjson.JSON;
import com.ax.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * className: LogAspect
 * description: 日志
 *
 * @author: axiang
 * date: 2023/4/10 0010 23:30
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.ax.annotation.SystemLog)")
    public void pt(){}

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result;
        long end = 0;
        long start = 0;
        try {
            start = System.currentTimeMillis();
            handleBefore(joinPoint);
            result = joinPoint.proceed();
            handleAfter(result);
            end = System.currentTimeMillis();
        } finally {
            // 计算接口运行所耗时长
            log.info("运行所耗时长: {}", (end - start) + "ms");
            // 结束后换行
            log.info("========================End========================" + System.lineSeparator());
        }
        return result;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);
        log.info("========================Start========================");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}",systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}",request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",joinPoint.getSignature().getDeclaringTypeName(),
                ((MethodSignature) joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()) );
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }

    private void handleAfter(Object result) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(result));
    }
}
