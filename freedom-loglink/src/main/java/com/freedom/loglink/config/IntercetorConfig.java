package com.freedom.loglink.config;

import com.freedom.loglink.interceptor.LogLinkIntercetpor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置自定义拦截器
 * @author: freedom
 * @Date: 2021/6/23 15:54
 */
@Configuration
public class IntercetorConfig implements WebMvcConfigurer {
    @Bean
    public LogLinkIntercetpor logLinkIntercetpor(){
        return new LogLinkIntercetpor();
    }
    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/server/getAll");
        excludePath.add("/health/check");
        registry.addInterceptor(logLinkIntercetpor()).addPathPatterns("/**").excludePathPatterns(excludePath);
    }
    //通用来出各种跨域的问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }
}
