package org.tuxdevelop.spring_boot_demo.service.intf;

import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageWrapperDTO;

import javax.jws.WebService;

@WebService(targetNamespace = "org.tuxdevelop.spring_boot_demo")
public interface MessageService extends CommonServiceInterface {

	//REST
	public static final String BASE_URI = REST_API + "/messages";
	public static final String SEND_MESSAGE_URI = BASE_URI;
	public static final String GET_RECEIVED_MESSAGES_URI = BASE_URI + "/received";
	public static final String GET_SEND_MESSAGES_URI = BASE_URI + "/send";

	//SOAP
	public static final String SOAP_SERVICE_NAME ="messageService";

	MessageDTO sendMessage(final MessageDTO messageDTO);

	MessageWrapperDTO getAllReceivedMessageDTOsByUserName(final String userName);

	MessageWrapperDTO getAllSendMessageDTOsByUserName(final String userName);
}
