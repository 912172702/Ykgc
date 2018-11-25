package com.prtr.ykgc.configuration;

import com.prtr.ykgc.business.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @Author: Knox
 * @Date: 2018/11/25 8:58 PM
 * @Description: You Know
 * @Version 1.0
 */
@Configuration
public class InterceptorAdapter extends WebMvcConfigurationSupport {
    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns(tokenInterceptor.getPathPatterns())
                .excludePathPatterns(tokenInterceptor.getExcludePathPatterns());
        super.addInterceptors(registry);
    }
}
