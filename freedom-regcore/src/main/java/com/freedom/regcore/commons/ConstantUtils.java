package com.freedom.regcore.commons;

import com.alibaba.fastjson.JSONObject;
import com.freedom.regcore.enums.BalanceEnum;
import com.freedom.regcore.utils.LoadBalance;
import io.micrometer.common.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * @author freedom
 * @Create on : 2023/12/26 17:17
 **/
public class ConstantUtils {

    public static volatile String BASE_URL = System.getProperty("freedom.config.host","http://127.0.0.1:8888/");

    public static final long HEARTBEAT_INTERVAL = 10 * 1000L; // 心跳包发送间隔，单位：毫秒

    public static final String SPECIAL ="#";

    public static final String HTTP = "http://" ;
    public static final String HTTPS = "https://" ;

    public static final String COLON = ":" ;
    public static final String LINE = "/" ;


    /**
     * 生成服务实例ID
     * @author freedom
     * @date 2023/12/31 2:55
     * @param serviceName 服务名
     * @param host 服务ip
     * @param port 服务端口
     * @return {@link String}
     */
    public static String getDefaultInstanceId(@NotNull String serviceName,@NotNull String host ,@NotNull int port){
        return serviceName + SPECIAL + host + SPECIAL + port ;
    }

    /**
     * 生成服务访问地址
     * @author freedom
     * @date 2023/12/31 2:55
     * @param serviceName 服务明
     * @param contextPath 根路径,默认服务名
     * @return {@link String} <a href="http://host:port/contextPath">...</a>
     */
    public static String getBaseUrl(@NotNull String serviceName,String contextPath,BalanceEnum balanceEnum){
        LoadBalance loadBalance = new LoadBalance(serviceName);
        JSONObject randomServer ;
        switch(balanceEnum){
            case ROBIN -> randomServer = loadBalance.getRobinServer();
            case CONNECT -> randomServer = loadBalance.getConnectionServer();
            case WEIGHT_RANDOM -> randomServer = loadBalance.getWeightRandomServer();
            case WEIGHT_ROBIN -> randomServer = loadBalance.getWeightRoundRobinServer();
            default -> randomServer = loadBalance.getRandomServer();
        }
        if(StringUtils.isNotEmpty(contextPath)){
            return HTTP+randomServer.getString("host")+COLON+randomServer.getInteger("port")+LINE+contextPath ;
        }
        return HTTP+randomServer.getString("host")+COLON+randomServer.getInteger("port")+LINE ;
    }
}
