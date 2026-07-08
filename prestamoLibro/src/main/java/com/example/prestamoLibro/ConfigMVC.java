package com.example.prestamoLibro;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigMVC implements WebMvcConfigurer {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("inicio");
        registry.addViewController("/error_403").setViewName("error/error_403");
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    

}
