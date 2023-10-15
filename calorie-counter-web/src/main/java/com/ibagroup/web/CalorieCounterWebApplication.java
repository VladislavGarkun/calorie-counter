package com.ibagroup.web;

import com.ibagroup.web.controller.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(value = { "com.ibagroup.common", "com.ibagroup.web"})
@PropertySource(value = { "application-common.properties", "application.properties"})
@SpringBootApplication
@EntityScan(value = "com.ibagroup.common.dao.postgres.model")
@EnableJpaRepositories(value = "com.ibagroup.common.dao.postgres.repository")
@EnableMongoRepositories(value = "com.ibagroup.common.dao.mongo.repository")
public class CalorieCounterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalorieCounterWebApplication.class);
    }

}