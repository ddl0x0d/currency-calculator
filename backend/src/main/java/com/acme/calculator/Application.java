package com.acme.calculator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        log.info("Starting application with args: {}", Arrays.toString(args));
        SpringApplication.run(Application.class, args);
    }
}
