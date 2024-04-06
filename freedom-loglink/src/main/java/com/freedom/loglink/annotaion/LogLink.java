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
    String value() default "";
    String methodName() default "" ;
}
