package com.catt.oauth2authserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@MapperScan("com.catt.oauth2authserver.dao")
public class Oauth2AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthServerApplication.class, args);
    }
}
