
package com.example.common;

import org.apache.ibatis.plugin.InterceptorChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JwtInterceptor jwtInterceptor;

    public WebConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    /*
        @Override
        public void configurePathMatch(PathMatchConfigurer configurer) {
            // 为所有 @RestController 添加统一接口前缀 "/api"
            configurer.addPathPrefix(
                    "/api",
                    clazz -> clazz.isAnnotationPresent(RestController.class)
            );
        }
         */
    //设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/qa").excludePathPatterns("/login");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/artifact").excludePathPatterns("/login");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/home").excludePathPatterns("/login");
    }
}


