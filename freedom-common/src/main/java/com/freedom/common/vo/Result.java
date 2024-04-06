package com.freedom.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应返回
 * @Author: zhangzengzeng
 * @Date: 2021/6/4 14:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> {
    /**
     * 响应码
     */
    private int code ;
    /**
     * 响应信息
     */
    private String msg ;
    /**
     * 相关数据
     */
    private T data ;

}
