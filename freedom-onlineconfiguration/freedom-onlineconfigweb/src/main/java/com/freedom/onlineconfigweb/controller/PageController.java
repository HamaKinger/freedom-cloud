package com.freedom.onlineconfigweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author freedom
 * @description
 * @date 2024/4/4 22:49
 */
@Controller
@Slf4j
public class PageController {

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

}
