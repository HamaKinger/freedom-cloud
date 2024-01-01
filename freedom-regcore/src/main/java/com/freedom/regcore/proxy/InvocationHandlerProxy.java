package com.freedom.regcore.proxy;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author freedom
 * @Create on : 2023/12/29 16:58
 **/
@Slf4j
public class InvocationHandlerProxy<T> implements InvocationHandler {
    private static final ThreadLocal<Stopwatch> STOPWATCH = new ThreadLocal<>();
    private T target ;

//    public RegInvocationHandlerProxy (Object target) {
//        this.target = target;
//    }

    public <R> R createProxy(T target){
        this.target = target;
        return (R) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke (Object proxy,Method method,Object[] args) throws Throwable {
        Object invoke = null;
        try {
            Stopwatch started = Stopwatch.createStarted();
            STOPWATCH.set(started);
            invoke = method.invoke(target,args);
            Stopwatch stopwatch = STOPWATCH.get();
            if (stopwatch == null) {
                log.debug("stopwatch ---> null");
            }else {
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                log.info("方法:{} , 请求耗时:{} ms", method.getName(),elapsed);
            }
        }finally {
            STOPWATCH.remove();
        }
        return invoke;
    }
}
