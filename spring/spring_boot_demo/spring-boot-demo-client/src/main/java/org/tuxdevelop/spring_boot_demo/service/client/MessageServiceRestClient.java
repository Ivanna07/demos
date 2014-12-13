package org.tuxdevelop.spring_boot_demo.service.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageWrapperDTO;
import org.tuxdevelop.spring_boot_demo.service.intf.MessageService;

public class MessageServiceRestClient extends AbstractRestClient implements MessageService {

	public MessageServiceRestClient(final RestTemplate restTemplate, final String userName, final String password,
			final String hostURL, final MediaType mediaType) {
		super(restTemplate, userName, password, hostURL, mediaType);
	}

	@Override
	public MessageDTO sendMessage(final MessageDTO messageDTO) {
		final HttpEntity<MessageDTO> requestEntity = new HttpEntity<MessageDTO>(messageDTO, httpHeaders);
		final ResponseEntity<MessageDTO> response = restTemplate.exchange(hostURL + MessageService.SEND_MESSAGE_URI,
				HttpMethod.POST, requestEntity, MessageDTO.class);
		evaluateResponse(response, HttpMethod.POST);
		return response.getBody();
	}

	@Override
	public MessageWrapperDTO getAllReceivedMessageDTOsByUserName(final String userName) {
		final HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);
		final String enrichedUrl = UriComponentsBuilder.fromUriString(MessageService.GET_RECEIVED_MESSAGES_URI)
				.queryParam("userName", userName).build().toString();
		final ResponseEntity<MessageWrapperDTO> response = restTemplate.exchange(hostURL + enrichedUrl, HttpMethod.GET,
				requestEntity, MessageWrapperDTO.class);
		evaluateResponse(response, HttpMethod.GET);
		return response.getBody();
	}

	@Override
	public MessageWrapperDTO getAllSendMessageDTOsByUserName(final String userName) {
		final HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);
		final String enrichedUrl = UriComponentsBuilder.fromUriString(MessageService.GET_SEND_MESSAGES_URI)
				.queryParam("userName", userName).build().toString();
		final ResponseEntity<MessageWrapperDTO> response = restTemplate.exchange(hostURL + enrichedUrl, HttpMethod.GET,
				requestEntity, MessageWrapperDTO.class);
		evaluateResponse(response, HttpMethod.GET);
		return response.getBody();
	}

}
