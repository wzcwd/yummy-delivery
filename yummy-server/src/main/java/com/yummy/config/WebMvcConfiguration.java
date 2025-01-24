package com.yummy.config;

import com.yummy.interceptor.JwtTokenAdminInterceptor;
import com.yummy.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * configuration class, register the component of web layer
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * register interceptor
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("start registering  interceptor ...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
    }


    /**
     * 设置静态资源映射
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * convert message format
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Create a new instance of MappingJackson2HttpMessageConverter
        // This converter handles JSON serialization and deserialization using Jackson
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Set an Object Mapper for converter, it can convert java object to json
        // JacksonObjectMapper is a customized Object Mapper that defines specific rules
        converter.setObjectMapper(new JacksonObjectMapper());
        // Add the custom converter, index 0 ensures it takes priority over default converters
        converters.add(0,converter);
    }
}
