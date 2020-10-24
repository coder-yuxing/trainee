package com.yuxing.trainee.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TraineeSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraineeSearchApplication.class, args);
    }


}
