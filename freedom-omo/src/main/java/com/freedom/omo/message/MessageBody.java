package com.freedom.omo.message;

/**
 * @author freedom
 * @description
 * @date 2024/3/10 16:54
 */
public class MessageBody {

    /**
     * 消息头
     */
    private String messageHead ;

    /**
     * 编码格式
     */
    private String encode;

    /**
     * 消息体
     */
    private byte[] messageBody ;

    /**
     * 消息类型
     */
    private MessageType messageType ;
    /**
    *消息签名
    */
    private String signature ;

    /**
     * 消息长度
     */
    private String length;

    /**
     * 状态
     */
    private String state ;
}
