package com.freedom.loglink.config;

import com.freedom.loglink.constant.Constant;
import com.freedom.loglink.utils.LocalThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

/**
 * 初始化默认线程池
 * @Author: zhangzengzeng
 * @Date: 2021/7/16 11:45
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Bean("asyncExecutor")
    public Executor asyncExecutor() {
        log.info("初始化线程池BeamName:{}","asyncExecutor");
        LocalThreadPoolTaskExecutor executor = new LocalThreadPoolTaskExecutor();
        executor.setCorePoolSize(Constant.CORE_POOL_SIZE);
        executor.setMaxPoolSize(Constant.CORE_POOL_SIZE*2);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(0);
        executor.setThreadNamePrefix("asyncExecutor");
        executor.initialize();
        return executor;
    }
}
