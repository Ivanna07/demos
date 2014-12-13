package org.tuxdevelop.spring_boot_demo.service.client;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;
import org.tuxdevelop.spring_boot_demo.service.intf.UserService;

@Slf4j
public class UserServiceRestClient extends AbstractRestClient implements UserService {

	public UserServiceRestClient(final RestTemplate restTemplate, final String userName, final String password,
			final String hostURL, final MediaType mediaType) {
		super(restTemplate, userName, password, hostURL, mediaType);
	}

	@Override
	public UserDTO getUser(final String userName) {
		final HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
		final String enrichedUrl = UriComponentsBuilder.fromUriString(UserService.GET_USER_URI)
				.queryParam("userName", userName).build().toString();
		final ResponseEntity<UserDTO> response = restTemplate.exchange(hostURL + enrichedUrl, HttpMethod.GET,
				httpEntity, UserDTO.class);
		evaluateResponse(response, HttpMethod.GET);
		return response.getBody();
	}

	@Override
	public UserDTO addUser(final UserDTO userDTO) {
		final HttpEntity<UserDTO> httpEntity = new HttpEntity<UserDTO>(userDTO, httpHeaders);
		log.info("" + httpEntity.getHeaders());
		final ResponseEntity<UserDTO> response = restTemplate.exchange(hostURL + UserService.ADD_USER_URI,
				HttpMethod.POST, httpEntity, UserDTO.class);
		evaluateResponse(response, HttpMethod.POST);
		return response.getBody();
	}

	@Override
	public void updateUser(final UserDTO userDTO) {
		final HttpEntity<UserDTO> httpEntity = new HttpEntity<UserDTO>(userDTO, httpHeaders);
		final ResponseEntity<Void> response = restTemplate.exchange(hostURL + UserService.UPDATE_USER_URI,
				HttpMethod.PUT, httpEntity, Void.class);
		evaluateResponse(response, HttpMethod.PUT);
	}

	@Override
	public void deleteUser(final String userName) {
		final HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
		final String enrichedUrl = UriComponentsBuilder.fromUriString(UserService.DELETE_USER_URI)
				.queryParam("userName", userName).build().toString();
		final ResponseEntity<Void> response = restTemplate.exchange(hostURL + enrichedUrl, HttpMethod.DELETE,
				httpEntity, Void.class);
		evaluateResponse(response, HttpMethod.DELETE);
	}
}
