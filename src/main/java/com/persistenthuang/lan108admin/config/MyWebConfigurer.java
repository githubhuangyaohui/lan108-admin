package com.persistenthuang.lan108admin.config;

import com.persistenthuang.lan108admin.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {


    //文件上传路径
    @Value("${file-save-path}")
    private String fileSavePath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/index")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/register")
                .excludePathPatterns("/api/email")
                .excludePathPatterns("/api/email")
                .excludePathPatterns("/api/logout")
                .excludePathPatterns("/api/home/**")
                .excludePathPatterns("/api/lan/**")
                .excludePathPatterns("/api/blogs/**")
                .excludePathPatterns("/api/view/**")
                .excludePathPatterns("/images/**");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + fileSavePath);
    }

    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    //websocket 配置
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

