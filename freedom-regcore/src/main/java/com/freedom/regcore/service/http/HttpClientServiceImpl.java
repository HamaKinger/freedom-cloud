package com.freedom.regcore.service.http;

import com.freedom.regcore.commons.ConstantUtils;
import com.freedom.regcore.commons.Constants;
import com.freedom.regcore.enums.BalanceEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * @author freedom
 * @Create on : 2023/12/30 22:19
 **/
@Slf4j
public class HttpClientServiceImpl<T> implements HttpClientService<T>{

    @Override
    public String execute (String baseUrl,String url,T param, String type) {
        return actuators(baseUrl,url,param,type);
    }

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
            log.error("post请求异常:{}",e.getMessage());
        }
        return null;
    }
}
