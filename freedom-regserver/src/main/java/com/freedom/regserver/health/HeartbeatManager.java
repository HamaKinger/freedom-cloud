package com.freedom.regserver.health;

import com.alibaba.fastjson.JSON;
import com.freedom.regcore.commons.ConstantUtils;
import com.freedom.regcore.enums.HeartbeatStatus;
import com.freedom.regcore.model.Instance;
import com.freedom.regserver.server.RegistryServer;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author freedom
 * @Create on : 2023/12/26 18:07
 **/
@Slf4j
public class HeartbeatManager {

    public static Map<String,List<Instance>> all = new RegistryServer().getAll();

    /**
     * 检测服务状态并标记
     * @author freedom
     * @date 2023/12/26 18:34
     */
    public void checkHeartbeat() {
        // 遍历所有服务实例
        Set<String> keys = all.keySet();
        for(String key : keys) {
            List<Instance> instances = all.get(key);
            for(Instance instance : instances) {
                log.debug("服务:{},查看时间:{}",JSON.toJSONString(instance),instance.getLastHeartTime());
                Instant instant = instance.getLastHeartTime().toInstant(ZoneOffset.of("+8"));
                long epochMilli = instant.toEpochMilli();
                // 如果在指定的时间内没有收到心跳包，则标记为不可用
                if(System.currentTimeMillis() - epochMilli > ConstantUtils.HEARTBEAT_INTERVAL) {
                    String host = instance.getHost();
                    int port = instance.getPort();
                    String sport = System.getProperty("port");
                    String address = System.getProperty("host");
                    if(port != Integer.parseInt(sport) && host.equals(address)) {
                        log.debug("服务host:port->{}:{}不可用",instance.getHost(),instance.getPort());
                        instance.setStatus(HeartbeatStatus.UNAVAILABLE);
                    }
                }
            }
        }
    }

    /**
     * 移除非健康服务
     * @author freedom
     * @date 2023/12/26 18:34
     */
    public void remove() {
        // 遍历所有服务实例
        Set<String> keys = all.keySet();
        for(String key : keys) {
            List<Instance> instances = all.get(key);
            for(int i = 0;i < instances.size();i++) {
                Instance instance = instances.get(i);
                HeartbeatStatus value = HeartbeatStatus.valueOf("UNAVAILABLE");
                HeartbeatStatus status = instance.getStatus();
                if(instances.removeIf(a -> value == status)){
                    log.debug("服务host:port->{}:{}不可用,即将被移除!!",instance.getHost(),instance.getPort());
                    all.remove(key);
                }
            }
        }
    }


    public void start(){
        new Thread(()->{
            while(true){
                checkHeartbeat();
                remove();
                try {
                    Thread.sleep(ConstantUtils.HEARTBEAT_INTERVAL);
                }catch(InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();
    }
}
