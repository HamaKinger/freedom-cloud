package com.freedom.onlineconfigweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author freedom
 * @description
 * @date 2024/4/4 16:14
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.freedom.*"})
public class OnlineConfigWebApp {
    public static void main (String[] args) {
        SpringApplication.run(OnlineConfigWebApp.class,args);
    }
}
