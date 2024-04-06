package com.freedom.loglink.constant;

import com.google.common.base.Stopwatch;

/**
 * @author freedom
 * @description
 * @date 2024/3/9 18:05
 */
public interface Constant {
    int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors()+1 ;
    String TRACE_ID = "TRACE_ID";

    ThreadLocal<Stopwatch> STOPWATCH_THREAD = new ThreadLocal<>();

}
