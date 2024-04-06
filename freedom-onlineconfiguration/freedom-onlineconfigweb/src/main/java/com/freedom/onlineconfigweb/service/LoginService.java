package com.freedom.onlineconfigweb.service;

import com.freedom.common.vo.Result;

/**
 * @author freedom
 * @description
 * @date 2024/4/5 0:55
 */
public interface LoginService {

    Result<String> sign (String username,String password,boolean remember);
}
