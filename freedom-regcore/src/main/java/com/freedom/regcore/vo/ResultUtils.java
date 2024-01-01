package com.freedom.regcore.vo;

import com.freedom.regcore.enums.ResultCode;

/**
 * @author freedom
 * @Create on : 2023/12/30 22:25
 **/
public class ResultUtils {

    private ResultUtils(){}

    public static <T> Result<T> buildResult(ResultCode code){
        return Result.<T>builder().code(code.getCode()).msg(code.getMsg()).build();
    }
    public static <T> Result<T> buildResult(ResultCode code,T data){
        return Result.<T>builder().code(code.getCode()).msg(code.getMsg()).data(data).build();
    }

    public static <T> Result<T> buildResult(int code,String msg,T data){
        return Result.<T>builder().code(code).msg(msg).data(data).build();
    }

    public static <T> Result<T> buildResult(int code,String msg){
        return Result.<T>builder().code(code).msg(msg).build();
    }
}
