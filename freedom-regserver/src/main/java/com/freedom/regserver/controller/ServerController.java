package com.freedom.regserver.controller;

import com.freedom.regcore.enums.ResultCodeEnum;
import com.freedom.regcore.model.Instance;
import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.vo.Result;
import com.freedom.regcore.vo.ResultUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author freedom
 * @Create on : 2023/12/26 19:34
 **/
@RestController
@Slf4j
@RequestMapping("/server")
public class ServerController {


    @Resource
    private ServiceRegistry serviceRegistry ;

    @GetMapping("/getAll")
    public Result<Map<String,List<Instance>>> getAll(){
        return ResultUtils.buildResult(ResultCodeEnum.SUCCESS,serviceRegistry.getAll());
    }
}
