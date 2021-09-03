package com.example.final_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  FinalProjectApplication is the main class
 */
@Slf4j
@SpringBootApplication
public class FinalProjectApplication {
    public static void main(String[] args) {
        log.info("Before Starting application");
        SpringApplication.run(FinalProjectApplication.class, args);
        log.info("Starting my application with {} args.", args.length);
    }
}
