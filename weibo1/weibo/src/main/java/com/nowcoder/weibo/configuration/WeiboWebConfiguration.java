package com.nowcoder.weibo.configuration;

import com.nowcoder.weibo.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by soyo pu on 2017/7/27.
 */
//这个是拦截器的入口，可以把拦截器加进来
    @Component
public class WeiboWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把拦截器注册进来
        registry.addInterceptor(passportInterceptor);
        super.addInterceptors(registry);
    }
}
