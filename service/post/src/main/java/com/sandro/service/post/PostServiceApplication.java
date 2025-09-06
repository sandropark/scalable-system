package com.sandro.service.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sandro.common", "com.sandro.service.post"})
@EntityScan(basePackages = {"com.sandro.common.domain", "com.sandro.service.post.domain"})
@EnableJpaRepositories(basePackages = "com.sandro.service.post.repository")
public class PostServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }
}