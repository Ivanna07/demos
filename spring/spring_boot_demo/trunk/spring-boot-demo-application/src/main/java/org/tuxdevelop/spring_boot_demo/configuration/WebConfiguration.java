package org.tuxdevelop.spring_boot_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration {

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

}
