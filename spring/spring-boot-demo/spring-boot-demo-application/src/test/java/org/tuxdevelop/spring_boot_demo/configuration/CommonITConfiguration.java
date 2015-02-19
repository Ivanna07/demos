package org.tuxdevelop.spring_boot_demo.configuration;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.tuxdevelop.spring_boot_demo.Application;

@Configuration
@ComponentScan(basePackages = "org.tuxdevelop.spring_boot_demo",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Application.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfiguration
                        .class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfiguration
                        .ApiWebSecurityConfigurerAdapter.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfiguration
                        .FormLoginWebSecurityConfigurerAdapter.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfiguration
                        .SoapWebSecurityConfigurerAdapter.class)})
@EntityScan(basePackages = "org.tuxdevelop.spring_boot_demo.persistence.domain")
@EnableJpaRepositories(basePackages = {"org.tuxdevelop.spring_boot_demo.repository"})
public class CommonITConfiguration {


}
