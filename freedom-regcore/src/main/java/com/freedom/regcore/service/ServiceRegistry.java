package com.freedom.regcore.service;

import com.freedom.regcore.model.Instance;

import java.util.List;
import java.util.Map;

/**
 * @author freedom
 * @Create on : 2023/12/26 14:56
 **/
public interface ServiceRegistry {
    void register(String serviceName, Instance instance);

    List<Instance> getInstances(String serviceName);

    Map<String,List<Instance>> getAll();
 }
