package org.tuxdevelop.spring_data_demo.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "org.tuxdevelop.spring_data_demo.rest")
public class SpringDataRestApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
    }

}
