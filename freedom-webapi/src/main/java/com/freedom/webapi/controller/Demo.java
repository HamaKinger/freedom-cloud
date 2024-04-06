package com.freedom.webapi.controller;

import com.freedom.regcore.api.client.RegistryClient;
import com.freedom.regcore.commons.Constants;
import com.freedom.regcore.enums.BalanceEnum;
import com.freedom.common.enums.ResultCodeEnum;
import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.http.HttpClientServiceImpl;
import com.freedom.regcore.service.impl.ServiceRegistryImpl;
import com.freedom.regcore.model.Instance;
import com.freedom.common.vo.Result;
import com.freedom.common.vo.ResultUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author freedom
 * @Create on : 2023/12/25 16:13
 **/
@RestController
@Slf4j
@RequestMapping("/get")
public class Demo {


    @Resource
    private Environment environment ;

/*    @Resource
    private HttpClientService<Object> objectHttpClientService ;*/

    @GetMapping("/getList")
    public Result<List<Instance>> getList(){
        ServiceRegistry serviceRegistry = new ServiceRegistryImpl();
        RegistryClient registryClient = new RegistryClient(serviceRegistry);
        String property = environment.getProperty("spring.application.name");
        List<Instance> instances = registryClient.getInstances(property);
        return ResultUtils.buildResult(ResultCodeEnum.SUCCESS,instances);
    }

    @GetMapping("/get1")
    public Result<String> getList1(@RequestParam(name = "key") String key){
        return ResultUtils.buildResult(ResultCodeEnum.SUCCESS,System.getProperty(key));
    }

    @PostMapping("/consumer")
    public Result<String> consumer(){
        HttpClientServiceImpl<Object> objectHttpClientService1 = new HttpClientServiceImpl<>();
        String execute = objectHttpClientService1.execute("freedom-producer","user",null,Constants.POST,BalanceEnum.RANDOM);
        return ResultUtils.buildResult(ResultCodeEnum.SUCCESS,execute);
    }
}
