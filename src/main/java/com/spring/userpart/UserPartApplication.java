package com.spring.userpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserPartApplication.class, args);
    }

}
