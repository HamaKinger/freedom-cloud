package com.freedom.regcore.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.freedom.regcore.commons.ConstantUtils;
import com.freedom.regcore.commons.Constants;
import com.freedom.common.enums.ResultCodeEnum;
import com.freedom.regcore.proxy.InvocationHandlerProxy;
import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.excute.RegistryServer;
import com.freedom.regcore.model.Instance;
import com.freedom.regcore.http.HttpClientService;
import com.freedom.regcore.http.HttpClientServiceImpl;
import com.freedom.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author freedom
 * @Create on : 2023/12/26 15:59
 **/
@Slf4j
public class ServiceRegistryImpl extends RegistryServer implements ServiceRegistry {


    @Override
    public void register (String serviceName,Instance instance) {
        JSONObject json = new JSONObject();
        json.put("serviceName",serviceName);
        json.put("instance",instance);
        HttpClientService<JSONObject> httpClientService = new HttpClientServiceImpl<>();
        InvocationHandlerProxy<HttpClientService<JSONObject>> objectInvocationHandlerProxy = new InvocationHandlerProxy<>();
        HttpClientService<JSONObject> proxy = objectInvocationHandlerProxy.createProxy(httpClientService);
        try {
            String post = proxy.execute(ConstantUtils.BASE_URL,Constants.SAVE_SERVER,json,Constants.POST);
            Result javaObject = JSON.toJavaObject(JSON.parseObject(post),Result.class);
            if(javaObject.getCode()== ResultCodeEnum.SUCCESS.getCode()){
                log.debug("服务{} 注册成功！",serviceName);
            }
        }catch(Exception e) {
            log.error("register-》JSON解析异常");
        }
    }

    @Override
    public List<Instance> getInstances (String serviceName) {
        HttpClientService<String> httpClientService = new HttpClientServiceImpl<>();
        InvocationHandlerProxy<HttpClientService<String>> objectInvocationHandlerProxy = new InvocationHandlerProxy<>();
        HttpClientService<String> proxy = objectInvocationHandlerProxy.createProxy(httpClientService);

        try {
            String execute = proxy.execute(ConstantUtils.BASE_URL,Constants.GET_SERVER,serviceName,Constants.GET);
            return (List<Instance>) JSON.toJavaObject(JSON.parseObject(execute),Result.class);
        }catch(Exception e) {
            log.error("getInstances-》JSON解析异常");
        }
        return Collections.emptyList();
    }

    @Override
    public Map<String,List<Instance>> getAll () {
        HttpClientService<String> httpClientService = new HttpClientServiceImpl<>();
        InvocationHandlerProxy<HttpClientService<String>> objectInvocationHandlerProxy = new InvocationHandlerProxy<>();
        HttpClientService<String> proxy = objectInvocationHandlerProxy.createProxy(httpClientService);
        try {
            String execute = proxy.execute(ConstantUtils.BASE_URL,Constants.GET_ALL,StringUtils.EMPTY,Constants.GET);
            if(!StringUtils.isEmpty(execute)){
                JSONObject jsonObject = JSON.parseObject(execute);
                Result javaObject = JSON.toJavaObject(jsonObject,Result.class);
                return (Map<String,List<Instance>>) javaObject.getData();
            }
        }catch(Exception e) {
            log.error("getAll-》JSON解析异常");
        }
        return Collections.emptyMap();
    }

    public static void main (String[] args) {
        String s = "" ;
        Result javaObject = JSON.toJavaObject(JSON.parseObject(s),Result.class);
        System.out.println("javaObject = " + javaObject);
    }
}
