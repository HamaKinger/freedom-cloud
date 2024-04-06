package com.freedom.regcore.http;

import com.freedom.regcore.commons.ConstantUtils;
import com.freedom.regcore.commons.Constants;
import com.freedom.regcore.enums.BalanceEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * @author freedom
 * @Create on : 2023/12/30 22:19
 **/
@Slf4j
@Service
public class HttpClientServiceImpl<T> implements HttpClientService<T>{

    @Override
    public String asyncExecute () {
        return null;
    }

    @Override
    public String execute (String serviceName,String url,T param,String type,BalanceEnum balanceEnum) {
        String baseUrl = ConstantUtils.getBaseUrl(serviceName,"",balanceEnum);
        return actuators(baseUrl,url,param,type);
    }

    private String actuators (String baseUrl,String url,T param,String type) {
        WebClient webClient = WebClientUtils.getWebClient(baseUrl);
        try {
            switch(type){
                case Constants.POST -> {
                    Mono<String> stringMono = webClient.post().uri(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(ObjectUtils.defaultIfNull(param,Collections.emptyMap()))
                            .retrieve()
                            .bodyToMono(String.class);
                    return stringMono.block();
                }
                case Constants.GET -> {
                    Mono<String> stringMono = webClient.get().uri(url,param)
                            .retrieve()
                            .bodyToMono(String.class);
                    return stringMono.block();
                }
                default -> log.info("未做更多适配");
            }
        }catch(Exception e) {
            log.error("post请求:baseurl:{}异常:{}",baseUrl+url,e.getMessage());
        }
        return null;
    }

    @Override
    public String execute (String baseUrl,String url,T param, String type) {
        return actuators(baseUrl,url,param,type);
    }
}
