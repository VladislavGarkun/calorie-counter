package com.ibagroup.bot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(value = {"com.ibagroup.bot", "com.ibagroup.common"})
@PropertySources({
        @PropertySource("classpath:application.yaml"),
        @PropertySource("classpath:application-common.properties")
})
@SpringBootApplication
@EnableMongoRepositories(value = "com.ibagroup.common")
public class CalorieCounterBotApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CalorieCounterBotApplication.class).web(WebApplicationType.NONE).run();
    }

}