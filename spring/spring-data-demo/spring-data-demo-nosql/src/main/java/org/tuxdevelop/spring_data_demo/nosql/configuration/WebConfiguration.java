package org.tuxdevelop.spring_data_demo.nosql.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration {

    @Bean
    WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
                viewControllerRegistry.addViewController("/add").setViewName("add");
                viewControllerRegistry.addViewController("/viewer").setViewName("viewer");
            }
        };
    }
}
