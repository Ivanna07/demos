package org.tuxdevelop.spring_boot_cxf_demo;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
@ComponentScan
@EntityScan(value = "org.tuxdevelop.spring_boot_cxf_demo.domain")
@EnableJpaRepositories(basePackages = "org.tuxdevelop.spring_boot_cxf_demo.repository")
@EnableAutoConfiguration
public class SpringBootCxfDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCxfDemoApplication.class, args);
    }

    @Configuration
    @ImportResource(value = "classpath*:cxfServletConfiguration.xml")
    public static class CxfWebConfiguration{

        @Bean
        public ServletRegistrationBean servletRegistrationBean() {
            CXFServlet cxfServlet = new CXFServlet();
            ServletRegistrationBean servletRegistrationBean =
                    new ServletRegistrationBean(cxfServlet, "/soap/*");
            servletRegistrationBean.setLoadOnStartup(1);
            return servletRegistrationBean;
        }
    }

    @Configuration
    public static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(final AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                        .withUser("root")
                            .password("root")
                            .roles("ROOT","USER")
                        .and()
                        .withUser("tux")
                            .password("tux")
                            .roles("USER");
        }
    }

}
