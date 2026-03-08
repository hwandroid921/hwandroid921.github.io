package com.tour.jeju.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/jeju/**", "/review/&&")
                .excludePathPatterns(
                        "/",
                        "/member/login",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**"
                );
    }
}
