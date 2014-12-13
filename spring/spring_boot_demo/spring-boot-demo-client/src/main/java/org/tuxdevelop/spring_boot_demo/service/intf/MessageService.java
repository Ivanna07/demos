package org.tuxdevelop.spring_boot_demo.service.intf;

import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageWrapperDTO;

public interface MessageService extends CommonServiceInterface {

	public static final String BASE_URI = REST_API + "/messages";
	public static final String SEND_MESSAGE_URI = BASE_URI;
	public static final String GET_RECEIVED_MESSAGES_URI = BASE_URI + "/received";
	public static final String GET_SEND_MESSAGES_URI = BASE_URI + "/send";

	MessageDTO sendMessage(final MessageDTO messageDTO);

	MessageWrapperDTO getAllReceivedMessageDTOsByUserName(final String userName);

	MessageWrapperDTO getAllSendMessageDTOsByUserName(final String userName);
}
