package com.freedom.regserver.server;

import com.alibaba.fastjson.JSON;
import com.freedom.regcore.model.Instance;
import com.freedom.regcore.service.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author freedom
 * @Create on : 2023/12/26 14:56
 **/
@Slf4j
public class RegistryServer implements ServiceRegistry {

    private static final Map<String,List<Instance>> SERVICE = new ConcurrentHashMap<>();

    public RegistryServer() {
        // TODO document why this constructor is empty
    }

    @Override
    public void register(String serviceName, Instance instance) {
        log.debug("register:{}",JSON.toJSONString(instance));
        SERVICE.computeIfAbsent(serviceName, k -> new ArrayList<>()).add(instance);
    }


    @Override
    public List<Instance> getInstances(String serviceName) {
        return SERVICE.get(serviceName);
    }



    @Override
    public Map<String,List<Instance>> getAll () {
        return SERVICE;
    }

}
