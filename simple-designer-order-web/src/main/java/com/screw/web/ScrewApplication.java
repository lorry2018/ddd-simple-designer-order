package com.screw.web;

import com.screw.AppConfiguration;
import com.screw.DomainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan
@Import({AppConfiguration.class, DomainConfiguration.class})
public class ScrewApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScrewApplication.class, args);
    }
}
