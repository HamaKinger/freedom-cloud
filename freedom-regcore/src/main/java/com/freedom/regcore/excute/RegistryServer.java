package com.freedom.regcore.excute;

import com.freedom.regcore.api.client.RegistryClient;
import com.freedom.regcore.commons.ConstantUtils;
import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.service.impl.ServiceRegistryImpl;
import com.freedom.regcore.enums.HeartbeatStatus;
import com.freedom.regcore.model.Instance;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * 注册对象
 * @author freedom
 * @Create on : 2023/12/3 16:46
 **/

@Data
@Slf4j
public class RegistryServer {
    /**
     * 服务名称
     */
    private String name ;

    /**
     * 服务ip
     */
    private String host ;

    /**
     * 服务端口号
     */
    private int port ;

    /**
     * 注册地址
     */
    private String destinationAddress;


    public void setName (String name) {
        this.name = name;
    }

    public void setHost (String host) {
        this.host = host;
    }

    public void setPort (int port) {
        this.port = port;
    }

    public void setDestinationAddress (String destinationAddress) {
        this.destinationAddress = "http://"+destinationAddress+"/";
    }

    public void initServer() throws UnknownHostException{
        port = port > 0 ? port : 8080;
        if(host==null || host.isEmpty()){
            //如果获取不到指定ip地址,默认为本机ip
            host= InetAddress.getLocalHost().getHostAddress();
        }
        ConstantUtils.BASE_URL = destinationAddress ;
        ServiceRegistry serviceRegistry = new ServiceRegistryImpl();
        RegistryClient registryClient = new RegistryClient(serviceRegistry);
        Instance instance = new Instance(name,host,port);
        instance.setLastHeartTime(LocalDateTime.now());
        instance.setStatus(HeartbeatStatus.AVAILABLE);
        registryClient.register(name,instance);
    }

}
