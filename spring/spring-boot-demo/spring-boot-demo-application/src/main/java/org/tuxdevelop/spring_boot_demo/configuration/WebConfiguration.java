package org.tuxdevelop.spring_boot_demo.configuration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ImportResource(value = "classpath*:spring/cxfServletConfiguration.xml")
public class WebConfiguration {

	/*
     * WebMVC Configuration
	 */

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(final ViewControllerRegistry viewControllerRegistry) {
                viewControllerRegistry.addViewController("/").setViewName("/");
                viewControllerRegistry.addViewController("/index").setViewName("index");
                viewControllerRegistry.addViewController("/message").setViewName("message");
                viewControllerRegistry.addViewController("/inbox").setViewName("inbox");
                viewControllerRegistry.addViewController("/outbox").setViewName("outbox");
                viewControllerRegistry.addViewController("/login").setViewName("login");
                viewControllerRegistry.addViewController("/register").setViewName("register");
                viewControllerRegistry.addViewController("/compose").setViewName("compose");
                viewControllerRegistry.addViewController("/user").setViewName("user");
                viewControllerRegistry.addViewController("/error").setViewName("error");
            }
        };
    }

	/*
	 * CXF Configuration
	 */

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        final CXFServlet cxfServlet = new CXFServlet();
        final ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(cxfServlet, "/soap/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

}
