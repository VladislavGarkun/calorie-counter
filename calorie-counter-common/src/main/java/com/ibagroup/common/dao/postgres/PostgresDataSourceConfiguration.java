package com.ibagroup.common.dao.postgres;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgresDataSourceConfiguration {

    //TODO refactor @Value via @ConfigurationProperties
    @Value(value = "${spring.data.postgres.driver}")
    private String driver;
    @Value(value = "${spring.data.postgres.url}")
    private String url;
    @Value(value = "${spring.data.postgres.username}")
    private String username;
    @Value(value = "${spring.data.postgres.password}")
    private String password;

    @Bean
    public DataSource postgresDataSource(){
        return DataSourceBuilder.create()
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

}
