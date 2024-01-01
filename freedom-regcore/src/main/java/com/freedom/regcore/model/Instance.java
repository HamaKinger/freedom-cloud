package com.freedom.regcore.model;


import com.freedom.regcore.enums.HeartbeatStatus;
import com.freedom.regcore.commons.ConstantUtils;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author freedom
 * @Create on : 2023/12/26 14:21
 **/
@Data
public class Instance {

    private String instanceId ;

    private String serviceName ;

    private String host;

    private int port;

    private HeartbeatStatus status ;

    private LocalDateTime lastHeartTime ;


    public Instance (String serviceName,String host,int port) {
        this.serviceName = serviceName;
        this.host = host;
        this.port = port;
        this.instanceId = ConstantUtils.getDefaultInstanceId(serviceName,host,port);
    }

}
