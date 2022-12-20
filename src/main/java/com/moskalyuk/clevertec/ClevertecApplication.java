package com.moskalyuk.clevertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaRepositories
public class ClevertecApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClevertecApplication.class, args);
    }

}
