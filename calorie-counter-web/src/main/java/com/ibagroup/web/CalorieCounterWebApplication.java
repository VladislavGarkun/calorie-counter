package com.ibagroup.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(value = { "com.ibagroup.common", "com.ibagroup.web"})
@PropertySource(value = { "application-common.properties", "application.properties"})
@SpringBootApplication
@EnableMongoRepositories(value = "com.ibagroup.common")
public class CalorieCounterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalorieCounterWebApplication.class);
    }

}