package com.freedom.regcore.enums;

/**
 * @Author: freedom
 * @Date: 2023/3/30 16:51
 */
public interface ResultCode {

    /**
     * 获取错误码
     */
    int getCode();

    /**
     * 获取错误信息
     */
    String getMsg();
}
