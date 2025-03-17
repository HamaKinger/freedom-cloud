package com.freedom.loglink.annotaion;

import java.lang.annotation.*;

/**
 * @author freedom
 * @description
 * @date 2024/3/9 17:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LogLink {
    /**
     * 描述
     * @return
     */
    String value() default "";

    /**
     * 方法名
     * @return
     */
    String methodName() default "" ;

    /**
     * 前缀标识
     * @return
     */
    String prefix() default "";
}
