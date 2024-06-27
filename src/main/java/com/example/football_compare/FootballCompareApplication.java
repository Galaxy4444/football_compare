package com.example.football_compare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FootballCompareApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballCompareApplication.class, args);
    }

}
