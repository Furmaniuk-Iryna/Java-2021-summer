package com.example.final_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FinalProjectApplication {
    public static void main(String[] args) {
        log.info("Application is started");
        SpringApplication.run(FinalProjectApplication.class, args);

    }
}
