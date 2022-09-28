package com.jxd.travel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName CrosConfig
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {
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
}
