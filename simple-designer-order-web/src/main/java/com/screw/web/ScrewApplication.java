package com.screw.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ScrewApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScrewApplication.class, args);
    }
}
