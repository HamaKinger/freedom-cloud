package com.freedom.regcore.service.http;

import com.freedom.regcore.enums.BalanceEnum;

/**
 * @author freedom
 * @Create on : 2023/12/30 22:18
 **/
public interface HttpClientService<T> {

    String execute(String baseUrl,String url ,T param,String type);

    String asyncExecute();

    /**
     *
     * @author freedom
     * @date 2024/1/1 0:22
     * @param serviceName
     * @param url
     * @param param
     * @param type
     * @param balanceEnum
     * @return {@link String}
     */
    String execute(String serviceName,String url ,T param,String type,BalanceEnum balanceEnum);
}
