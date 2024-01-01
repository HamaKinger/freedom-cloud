package com.freedom.regcore.api.client;

import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.model.Instance;

import java.util.List;

/**
 * 提供服务端查询
 * @author freedom
 * @Create on : 2023/12/26 14:56
 **/
public class RegistryClient {

    private ServiceRegistry registry;

    public RegistryClient(ServiceRegistry registry) {
        this.registry = registry;
    }

    public void register(String serviceName, Instance instance) {
        registry.register(serviceName, instance);
    }

    public List<Instance> getInstances(String serviceName) {
        return registry.getInstances(serviceName);
    }

}
