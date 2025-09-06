package com.sandro.service.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sandro.common", "com.sandro.service.comment"})
@EntityScan(basePackages = {"com.sandro.common.domain", "com.sandro.service.comment.domain"})
@EnableJpaRepositories(basePackages = "com.sandro.service.comment.repository")
public class CommentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentServiceApplication.class, args);
    }
}