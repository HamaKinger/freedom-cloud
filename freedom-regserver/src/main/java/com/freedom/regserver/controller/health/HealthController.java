package com.freedom.regserver.controller.health;

import com.alibaba.fastjson.JSONObject;
import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.enums.HeartbeatStatus;
import com.freedom.regcore.model.Instance;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author freedom
 * @Create on : 2023/12/26 19:40
 **/
@RestController
@Slf4j
@RequestMapping("/health")
public class HealthController {

    @Resource
    private ServiceRegistry serviceRegistry;

    @PostMapping("/check")
    public String check(@RequestBody JSONObject json){
        String serviceName = json.getString("serviceName");
        JSONObject instance = json.getJSONObject("instance");
        Instance object = instance.toJavaObject(Instance.class);
        Integer port = instance.getInteger("port");
        String host = instance.getString("host");
        //检测 和 注册
        Map<String,List<Instance>> all = serviceRegistry.getAll();
        Set<String> strings = all.keySet();
        if(strings.contains(serviceName)){
            for(List<Instance> ins : all.values()) {
                for(Instance inst : ins) {
                    if(host.equals(inst.getHost()) && port == inst.getPort()) {
                        inst.setLastHeartTime(LocalDateTime.now());
                    }
                }
            }
        }else {
            object.setLastHeartTime(LocalDateTime.now());
            object.setStatus(HeartbeatStatus.AVAILABLE);
            serviceRegistry.register(serviceName,object);
        }
        return "ok";
    }
}
