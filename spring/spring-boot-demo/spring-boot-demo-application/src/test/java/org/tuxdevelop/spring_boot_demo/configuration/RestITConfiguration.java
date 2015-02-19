package org.tuxdevelop.spring_boot_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.tuxdevelop.spring_boot_demo.service.client.MessageServiceRestClient;
import org.tuxdevelop.spring_boot_demo.service.client.UserServiceRestClient;

@Configuration
public class RestITConfiguration {

	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	private static final String HOST_URL = "http://localhost:8080";
	private static final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean
	public UserServiceRestClient userServiceRestClient() {
		return new UserServiceRestClient(restTemplate(), USER_NAME, PASSWORD, HOST_URL, MEDIA_TYPE);
	}

	@Bean
	public MessageServiceRestClient messageServiceRestClient() {
		return new MessageServiceRestClient(restTemplate(), USER_NAME, PASSWORD, HOST_URL, MEDIA_TYPE);
	}

}
