package com.freedom.regcore.health;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.freedom.regcore.commons.ConstantUtils;
import com.freedom.regcore.commons.Constants;
import com.freedom.regcore.model.Instance;
import com.freedom.regcore.proxy.InvocationHandlerProxy;
import com.freedom.regcore.http.HttpClientService;
import com.freedom.regcore.http.HttpClientServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author freedom
 * @Create on : 2023/12/26 17:55
 **/
@Slf4j
public class CheckHealth {

    private CheckHealth(){
    }

    public static void check(String serviceName,Instance instance){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serviceName",serviceName);
        jsonObject.put("instance",instance);
        HttpClientService<JSON> objectHttpClientService = new HttpClientServiceImpl<>();
        InvocationHandlerProxy<HttpClientService<JSON>> objectInvocationHandlerProxy = new InvocationHandlerProxy<>();
        HttpClientService<JSON> proxy = objectInvocationHandlerProxy.createProxy(objectHttpClientService);
        String execute = proxy.execute(ConstantUtils.BASE_URL,Constants.HEALTH_CHECK,jsonObject,Constants.POST);
        log.debug("check通信返回:{}",execute);
    }
}
