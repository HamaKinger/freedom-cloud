package com.freedom.regcore.excute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.freedom.regcore.commons.ConstantUtils;
import com.freedom.regcore.health.CheckHealth;
import com.freedom.regcore.model.Instance;
import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.service.impl.ServiceRegistryImpl;
import com.freedom.regcore.utils.ServiceCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author freedom
 * @Create on : 2023/12/3 17:42
 **/
@Slf4j
public class SpringExecutor extends RegistryServer implements ApplicationContextAware, SmartInitializingSingleton, DisposableBean {


    @Override
    public void destroy () throws Exception {

    }

    @Override
    public void afterSingletonsInstantiated () {
        try {
            super.initServer();
            run();
        }catch(UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        SpringExecutor.applicationContext = applicationContext ;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void run(){
        Instance instance = new Instance(getName(),getHost(),getPort());
        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(ConstantUtils.HEARTBEAT_INTERVAL);
                }catch(InterruptedException e) {
                    throw new RuntimeException(e);
                }
                CheckHealth.check(getName(),instance);
                getAllServer();
            }
        }).start();
    }

    public void getAllServer(){
        ServiceRegistry serviceRegistry = new ServiceRegistryImpl();
        Map<String,List<Instance>> all = serviceRegistry.getAll();
        if(!all.isEmpty()){
            Set<String> keys = all.keySet();
            keys.forEach(key->{
                        //缓存
                        List<Instance> instances = all.get(key);
                        JSONArray objects = JSON.parseArray(JSON.toJSONString(instances));
                        ServiceCacheUtil.SERVICE.put(key,objects);
                    }
            );
        }
    }
}
