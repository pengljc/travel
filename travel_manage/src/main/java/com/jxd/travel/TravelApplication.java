package com.jxd.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName TravelApplication
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.jxd.travel.mapper")
public class TravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class,args);
    }
}
