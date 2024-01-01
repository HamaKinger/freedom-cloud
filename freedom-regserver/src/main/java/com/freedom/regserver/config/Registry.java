package com.freedom.regserver.config;

import com.freedom.regcore.enums.HeartbeatStatus;
import com.freedom.regcore.model.Instance;
import com.freedom.regserver.health.HeartbeatManager;
import com.freedom.regserver.server.RegistryServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author freedom
 * @Create on : 2023/12/26 14:22
 **/
@Configuration
public class Registry {

    @Value("${spring.application.name}")
    private String serverName ;
    @Value("${server.port}")
    private String port ;
    @Value("${server.address}")
    private String host ;


    @Bean
    public void register(){
        System.setProperty("port",port);
        System.setProperty("host",host);
        RegistryServer registryServer = new RegistryServer();
        Instance instance = new Instance(serverName,host,Integer.parseInt(port));
        instance.setLastHeartTime(LocalDateTime.now());
        instance.setStatus(HeartbeatStatus.AVAILABLE);
        registryServer.register(serverName,instance);
        HeartbeatManager heartbeatManager = new HeartbeatManager();
        heartbeatManager.start();
    }
}