package com.freedom.webapi.config;

import com.freedom.regcore.excute.SpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author freedom
 * @Create on : 2023/12/3 17:50
 **/
@Configuration
@Slf4j
public class RegistryConfig {

    @Value("${freedom.registry.host}")
    private String host ;
    @Value("${freedom.registry.name}")
    private String name ;
    @Value("${freedom.registry.port}")
    private int port ;
    @Value("${freedom.registry.destinationAddress}")
    private String destinationAddress;
    @Bean
    public SpringExecutor registryInit(){
        System.setProperty("freedom.config.host",host);
        SpringExecutor springServer = new SpringExecutor();
        springServer.setHost(host);
        springServer.setName(name);
        springServer.setPort(port);
        springServer.setDestinationAddress(destinationAddress);
        return springServer;
    }
}
