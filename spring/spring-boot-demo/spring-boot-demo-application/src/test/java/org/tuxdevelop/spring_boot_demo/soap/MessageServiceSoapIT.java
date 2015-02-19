package org.tuxdevelop.spring_boot_demo.soap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_demo.configuration.SoapITConfiguration;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageWrapperDTO;
import org.tuxdevelop.spring_boot_demo.service.intf.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoapITConfiguration.class)
public class MessageServiceSoapIT {

	private static final String USER_NAME = "root";

	@Autowired
	private MessageService messageServiceSoap;

	@Test
	public void getSendMessageIT() {
		final MessageWrapperDTO sendMessages = messageServiceSoap.getAllSendMessageDTOsByUserName(USER_NAME);
		Assert.assertNotNull(sendMessages);
		Assert.assertFalse(sendMessages.getMessageDTOs().isEmpty());
	}

	@Test
	public void endMessageIT() {
		final MessageDTO messageDTO = new MessageDTO();
		messageDTO.setEmailAddress("root@spring.boot");
		messageDTO.setMessage("Hello, Spring Boot");
		messageDTO.setSenderUserName("root");
		final MessageDTO sendMessage = messageServiceSoap.sendMessage(messageDTO);
		Assert.assertNotNull(sendMessage);
	}

	@Test
	public void getReceivedMessageIT() {
		final MessageWrapperDTO receivedMessages = messageServiceSoap
				.getAllReceivedMessageDTOsByUserName(USER_NAME);
		Assert.assertNotNull(receivedMessages);
		Assert.assertFalse(receivedMessages.getMessageDTOs().isEmpty());
	}
}
