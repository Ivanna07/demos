package org.tuxdevelop.spring_boot_cxf_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringBootCxfDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCxfDemoApplication.class, args);
    }
}
