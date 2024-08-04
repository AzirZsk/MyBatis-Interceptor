package com.azir.mybatisinterceptor.config;

import com.azir.mybatisinterceptor.interceptor.TenantSpringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangshukun
 * @date 2024/8/4
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantSpringInterceptor())
                .addPathPatterns("/**");
    }
}
