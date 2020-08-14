package com.example.dyq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DyqApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyqApplication.class, args);
    }

}
