package com.freedom.regcore.http;

import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author freedom
 * @Create on : 2023/12/30 23:04
 **/
public class WebClientUtils {
    private WebClientUtils(){}

    public static WebClient getWebClient (String baseurl) {
        return WebClient.builder().baseUrl(baseurl).build();
    }

}
