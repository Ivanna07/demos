package org.tuxdevelop.spring_boot_demo.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.tuxdevelop.spring_boot_demo.Application;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "org.tuxdevelop.spring_boot_demo",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Application.class),
                          @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfiguration.class)})
@EnableJpaRepositories(basePackages = {"org.tuxdevelop.spring_boot_demo.repository"})
public class CommonITConfiguration {


}
