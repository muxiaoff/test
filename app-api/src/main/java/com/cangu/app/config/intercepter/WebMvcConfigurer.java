package com.cangu.app.config.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by byw on 2019/28/19.
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Bean
    public WebMvcConfigurer getInterfaceAuthCheckInterceptor() {
        return new WebMvcConfigurer();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /** 指定拦截器类 */
        registry.addInterceptor(new BootInterceptor())
                /** 指定该类拦截的url */
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
}
