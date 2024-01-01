package com.freedom.regcore.service.http;

import com.freedom.regcore.model.Instance;
import com.freedom.regcore.service.ServiceRegistry;
import com.freedom.regcore.service.impl.ServiceRegistryImpl;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

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
