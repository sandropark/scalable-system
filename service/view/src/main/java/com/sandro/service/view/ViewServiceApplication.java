package com.sandro.service.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sandro.common", "com.sandro.service.view"})
@EntityScan(basePackages = {"com.sandro.common.domain", "com.sandro.service.view.domain"})
@EnableJpaRepositories(basePackages = "com.sandro.service.view.repository")
public class ViewServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ViewServiceApplication.class, args);
    }
}