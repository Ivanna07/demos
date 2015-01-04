package org.tuxdevelop.spring_data_demo.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.tuxdevelop.spring_data_demo.solr")
public class SpringDataSolrApplication {

    public static void main(String[] args){
        SpringApplication.run(SpringDataSolrApplication.class,args);
    }
}
