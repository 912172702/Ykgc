package com.prtr.ykgc.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.prtr.ykgc.business.interceptor.TokenInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/11/25 8:58 PM
 * @Description: You Know
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    @Resource
    private TokenInterceptor tokenInterceptor;

    /**
     * 注册一个验证Token的拦截器
     *
     * @param registry 拦截器注册
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        logger.info("不必验证的url ： " + tokenInterceptor.getExcludePathPatterns());

        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns(tokenInterceptor.getExcludePathPatterns())
                .addPathPatterns(tokenInterceptor.getPathPatterns());

        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
    }

    /**
     * 加入一个ObjectMapper，使返回空的Object时候不报错
     *
     * @param converters 转换器
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        converter.setObjectMapper(objectMapper);
        converters.add(converter);
        super.configureMessageConverters(converters);
    }
}
