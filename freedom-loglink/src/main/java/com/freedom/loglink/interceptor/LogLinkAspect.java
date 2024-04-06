package com.freedom.loglink.interceptor;

import com.freedom.loglink.annotaion.LogLink;
import com.freedom.loglink.constant.Constant;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.freedom.loglink.constant.Constant.STOPWATCH_THREAD;


/**
 * @author freedom
 * @description
 * @date 2024/3/9 18:40
 */
@Slf4j
@Aspect
@Component
public class LogLinkAspect {

    @Before("@annotation(com.freedom.loglink.annotaion.LogLink)")
    public void before(JoinPoint joinPoint) {
        Stopwatch started = Stopwatch.createStarted();
        STOPWATCH_THREAD.set(started);
        extracted();
    }

    @After("@annotation(com.freedom.loglink.annotaion.LogLink)")
    public void after(JoinPoint joinPoint) {
        try {
            Stopwatch stopwatch = STOPWATCH_THREAD.get();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            LogLink logLink = signature.getMethod().getAnnotation(LogLink.class);
            if (stopwatch == null) {
                return;
            }
            long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            log.info("{};方法:{}请求耗时:{} ms", logLink.value(),logLink.methodName(),elapsed);
        }finally {
            STOPWATCH_THREAD.remove();
            MDC.remove(Constant.TRACE_ID);
        }

    }

    private static void extracted () {
        //MDC链路日志配置
        String tid = RandomStringUtils.randomAlphanumeric(8);
        //利用MDC将请求存储
        MDC.put(Constant.TRACE_ID,tid);
    }
}