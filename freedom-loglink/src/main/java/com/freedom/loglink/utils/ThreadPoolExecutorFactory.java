package com.freedom.loglink.utils;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: ZhangZengZeng
 * @Date: 2023/3/31 16:34
 */
public class ThreadPoolExecutorFactory{

    private ThreadPoolExecutorFactory (){
    }
    public static LocalThreadPoolTaskExecutor get() {
        LocalThreadPoolTaskExecutor executor = new LocalThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(12);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(0);
        executor.initialize();
        return executor;
    }

    public static LocalThreadPoolTaskExecutor get(Integer queueSize) {
        LocalThreadPoolTaskExecutor executor = new LocalThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(12);
        executor.setQueueCapacity(queueSize);
        executor.setKeepAliveSeconds(0);
        executor.initialize();
        return executor;
    }


    public static LocalThreadPoolTaskExecutor getCallerRunsPolicy(Integer queueSize){
        LocalThreadPoolTaskExecutor executor = new LocalThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(12);
        executor.setQueueCapacity(queueSize);
        executor.setKeepAliveSeconds(0);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
