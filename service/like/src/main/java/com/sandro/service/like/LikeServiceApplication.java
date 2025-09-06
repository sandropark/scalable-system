package com.sandro.service.like;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sandro.common", "com.sandro.service.like"})
@EntityScan(basePackages = {"com.sandro.common.domain", "com.sandro.service.like.domain"})
@EnableJpaRepositories(basePackages = "com.sandro.service.like.repository")
public class LikeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeServiceApplication.class, args);
    }
}