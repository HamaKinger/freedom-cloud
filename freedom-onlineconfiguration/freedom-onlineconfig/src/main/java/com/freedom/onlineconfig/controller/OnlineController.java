package com.freedom.onlineconfig.controller;

import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.fastjson.JSONObject;
import com.freedom.common.enums.ResultCodeEnum;
import com.freedom.common.vo.Result;
import com.freedom.common.vo.ResultUtils;
import com.freedom.onlineconfig.vo.ConfigVo;
import com.freedom.regcore.commons.Constants;
import com.freedom.regcore.enums.BalanceEnum;
import com.freedom.regcore.http.HttpClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author freedom
 * @description
 * @date 2024/4/4 14:55
 */
@RestController
@RequestMapping("/config")
@Slf4j
public class OnlineController {
    @Value("${spring.application.name}")
    private String serverName ;

    @Autowired
    private HttpClientService httpClientService ;

    @PostMapping("/edit")
    public Result save(@RequestBody ConfigVo configVo){
        String serviceName = configVo.getServiceName();
        boolean equals = CharSequenceUtil.equals(serviceName,serverName);
        if(equals){
            System.setProperty(configVo.getKey(),configVo.getValue());
            return ResultUtils.buildResult(ResultCodeEnum.SUCCESS);
        }else {
            String execute = httpClientService.execute(serviceName,"config/edit",configVo,Constants.POST,BalanceEnum.RANDOM);
            return JSONObject.toJavaObject(JSONObject.parseObject(execute),Result.class);
        }
    }

    @PostMapping("/del")
    public Result del(@RequestBody ConfigVo configVo){
        String serviceName = configVo.getServiceName();
        boolean equals = CharSequenceUtil.equals(serviceName,serverName);
        if(equals){
            System.clearProperty(configVo.getKey());
            return ResultUtils.buildResult(ResultCodeEnum.SUCCESS);
        }else {
            String execute = httpClientService.execute(serviceName,"config/del",configVo,Constants.POST,BalanceEnum.RANDOM);
            return JSONObject.toJavaObject(JSONObject.parseObject(execute),Result.class);
        }
    }
}
