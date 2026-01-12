package com.surveysdk.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing // Identifies fields with @CreatedDate and fill them automatically.
public class SurveySdkServerApplication {

    public static void main(String[] args) {
        System.out.println("MONGODB_URI from env = " + System.getenv("MONGODB_URI"));
        SpringApplication.run(SurveySdkServerApplication.class, args);
    }

}
