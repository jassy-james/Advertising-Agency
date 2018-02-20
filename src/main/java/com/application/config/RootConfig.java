package com.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

//класс для статических ресурсов и отображения

@Configuration
@EnableWebMvc
@ComponentScan("com.application")
public class RootConfig extends WebMvcConfigurerAdapter {

    //переопределив данный метод мы можем указать где будут лежать
    //статические ресурсы проекта (css, js и т.д.)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        registry.viewResolver(viewResolver);
    }

    //метод для отображения не .jsp файлов, а .html
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
