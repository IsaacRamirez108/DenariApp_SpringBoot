package com.denarisolutions.denariapp_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class DenariAppSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DenariAppSpringBootApplication.class, args);
    }

}
