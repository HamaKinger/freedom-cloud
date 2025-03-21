package com.freedom.regserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.freedom.regcore.enums.HeartbeatStatus;
import com.freedom.common.enums.ResultCodeEnum;
import com.freedom.common.vo.Result;
import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.model.Instance;
import com.freedom.common.vo.ResultUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author freedom
 * @Create on : 2023/12/26 15:18
 **/
@RestController
@Slf4j
@RequestMapping("/client")
public class ClientController {

    @Resource
    private ServiceRegistry serviceRegistry;
    @GetMapping("/get/{service}")
    public Result<List<Instance>> get(@PathVariable(name = "service") String service){
        List<Instance> instances = serviceRegistry.getInstances(service);
        return ResultUtils.buildResult(ResultCodeEnum.SUCCESS,instances);
    }

    @PostMapping("/save")
    public Result save(@RequestBody JSONObject json){
        log.debug("服务信息注册信息{}",json.toString());
        String serviceName = json.getString("serviceName");
        JSONObject instanceJson = json.getJSONObject("instance");
        Instance instance = new Instance(serviceName,instanceJson.getString("host"),instanceJson.getInteger("port"));
        instance.setLastHeartTime(LocalDateTime.now());
        instance.setStatus(HeartbeatStatus.AVAILABLE);
        serviceRegistry.register(serviceName,instance);
        return ResultUtils.buildResult(ResultCodeEnum.SUCCESS);
    }
}
