package org.tuxdevelop.spring_boot_demo.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_demo.configuration.RestITConfiguration;
import org.tuxdevelop.spring_boot_demo.service.client.MessageServiceRestClient;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageWrapperDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestITConfiguration.class)
public class MessageRestControllerIT {

	private static final String USER_NAME = "root";

	@Autowired
	private MessageServiceRestClient messageServiceRestClient;

	@Test
	public void getSendMessageIT() {
		final MessageWrapperDTO sendMessages = messageServiceRestClient.getAllSendMessageDTOsByUserName(USER_NAME);
		Assert.assertNotNull(sendMessages);
		Assert.assertFalse(sendMessages.getCollection().isEmpty());
	}

	@Test
	public void endMessageIT() {
		final MessageDTO messageDTO = new MessageDTO();
		messageDTO.setEmailAddress("root@spring.boot");
		messageDTO.setMessage("Hello, Spring Boot");
		messageDTO.setSenderUserName("root");
		final MessageDTO sendMessage = messageServiceRestClient.sendMessage(messageDTO);
		Assert.assertNotNull(sendMessage);
	}

	@Test
	public void getReceivedMessageIT() {
		final MessageWrapperDTO receivedMessages = messageServiceRestClient
				.getAllReceivedMessageDTOsByUserName(USER_NAME);
		Assert.assertNotNull(receivedMessages);
		Assert.assertFalse(receivedMessages.getCollection().isEmpty());
	}
}
