package com.sandro.service.postread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sandro.common", "com.sandro.service.postread"})
@EntityScan(basePackages = {"com.sandro.common.domain", "com.sandro.service.post.domain", "com.sandro.service.comment.domain"})
@EnableJpaRepositories(basePackages = "com.sandro.service.postread.repository")
public class PostReadServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostReadServiceApplication.class, args);
    }
}