package com.freedom.onlineconfigweb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author freedom
 * @description
 * @date 2024/4/4 22:54
 */
@Component
public class StartConfig implements CommandLineRunner {
    private final ConfigurableApplicationContext application;

    public StartConfig (ConfigurableApplicationContext application) {
        this.application = application;
    }
    @Override
    public void run (String... args) throws UnknownHostException {
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        String name = env.getProperty("spring.application.name");
        System.out.println("---------------------------------------------");
        System.out.println("application name -> :["+name+"]");
        System.out.println("Local :     [http://localhost:"+port+path+"]");
        System.out.println("External :  [http://"+ip+":"+port+path+"]");
        System.out.println("---------------------------------------------");

    }
}
