package com.yins.health.conf;

import com.yins.health.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/account/v1/login", "/api/account/v1/register","/api/image/v1/*",
                    "/api/account/v1/authentication","/api/phone/v1/**","/api/account/v1/jssdk-1",
                    "/api/account/v1/jssdk","/api/file/v1/upload","/api/file/v1/mediaUrl","/api/file/v1/weixinUrl"
            );
    }
}
