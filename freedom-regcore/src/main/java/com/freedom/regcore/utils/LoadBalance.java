package com.freedom.regcore.utils;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author freedom
 * @Create on : 2023/12/30 14:30
 **/
public class LoadBalance {

    private final JSONArray instances ;
    private final AtomicInteger current = new AtomicInteger(0);
    public LoadBalance(String serviceName) {
        this.instances = ServiceCacheUtil.SERVICE.get(serviceName);
    }

    /**
     * random
     * @author freedom
     * @date 2023/12/30 15:45
     */
    public JSONObject getRandomServer() {
        if (instances.isEmpty()) {
            return null;
        }
        int i = RandomUtil.randomInt(instances.size());
        return instances.getJSONObject(i);
    }
    public JSONObject getRobinServer() {
        if (instances.isEmpty()) {
            return null;
        }
        int andIncrement = current.getAndIncrement();
        if(andIncrement>instances.size()){
            current.set(0);
        }
        return instances.getJSONObject(andIncrement % instances.size());
    }
    public JSONObject getWeightRoundRobinServer() {
        return null ;
    }
    public JSONObject getWeightRandomServer() {
        return null ;
    }

    public JSONObject getConnectionServer() {
        return null ;
    }
}
