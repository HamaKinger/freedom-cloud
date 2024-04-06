package com.freedom.loglink.interceptor;

import com.freedom.loglink.constant.Constant;
import com.google.common.base.Stopwatch;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.TimeUnit;

import static com.freedom.loglink.constant.Constant.STOPWATCH_THREAD;

/**
 * @Author: zengzeng
 * @Date: 2021/05/30 0030 20:46
 */
@Slf4j
public class LogLinkIntercetpor implements HandlerInterceptor {
    

    //执行控制器方法前激发
    @Override
    public boolean preHandle (HttpServletRequest request,HttpServletResponse response,Object handler) {
        Stopwatch started = Stopwatch.createStarted();
        STOPWATCH_THREAD.set(started);
        extracted(request);
        return true;
    }

    private static void extracted (HttpServletRequest request) {
        //MDC链路日志配置
        String tid = RandomStringUtils.randomAlphanumeric(8);
        if(!StringUtils.isEmpty(request.getHeader(Constant.TRACE_ID))){
            tid= request.getHeader(Constant.TRACE_ID);
        }
        //利用MDC将请求存储
        MDC.put(Constant.TRACE_ID,tid);
    }

    //执行玩控制器方法的业务逻辑后触发
    @Override
    public void postHandle (HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception {
    }

    //执行完控制器逻辑视图后,在进入视图解析器前触发
    @Override
    public void afterCompletion (HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception {
        try {
            Stopwatch stopwatch = STOPWATCH_THREAD.get();
            if (stopwatch == null) {
                return;
            }
            long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            log.info("接口:{} , 请求耗时:{} ms", request.getRequestURI(),elapsed);
        }finally {
            STOPWATCH_THREAD.remove();
            MDC.remove(Constant.TRACE_ID);
        }
    }
}
