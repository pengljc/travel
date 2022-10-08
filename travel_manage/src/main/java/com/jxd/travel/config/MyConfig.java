package com.jxd.travel.config;

import com.jxd.travel.util.SystemAPI;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName CrosConfig
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
    //跨域处理
    @Override
    public void addCorsMappings(CorsRegistry registry){
        ///**匹配的是我们所有后台的路径，代表后台共享了什么资源
        registry.addMapping("/**")
                //匹配的前台的服务器地址
                .allowedOrigins("*")
                //允许的前台的请求方式
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
/*
    //资源处理，前端通过url访问后端服务器上的图片
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/tickets_img/**").addResourceLocations("file:" + SystemAPI.filePath);
    }*/
}
