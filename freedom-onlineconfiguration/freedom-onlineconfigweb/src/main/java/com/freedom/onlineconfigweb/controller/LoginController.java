package com.freedom.onlineconfigweb.controller;

import com.freedom.common.vo.Result;
import com.freedom.onlineconfigweb.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author freedom
 * @description
 * @date 2024/4/5 0:40
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService ;
    @PostMapping("/sign")
    public Result<String> sign(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("remember") boolean remember){
        return loginService.sign(username,password,remember);
    }

}
