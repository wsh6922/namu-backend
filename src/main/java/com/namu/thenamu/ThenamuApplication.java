package com.namu.thenamu;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.TimeZone;

@SpringBootApplication
public class ThenamuApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:aws-config.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(ThenamuApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
