package org.tuxdevelop.spring_boot_demo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.tuxdevelop.spring_boot_demo.configuration.CommonITConfiguration;
import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

import java.util.List;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommonITConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageServiceBeanIT extends ServiceITBase {

    @Autowired
    private org.tuxdevelop.spring_boot_demo.service.MessageServiceBean messageServiceBean;

    @Autowired
    private org.tuxdevelop.spring_boot_demo.service.UserServiceBean userServiceBean;

    @Test
    public void sendMessageIT() {

        final String senderUserName = "tuxdevelop";
        final String recipientEmailAddress = "donnie@frank.org";
        final String messageText = "Hello Donnie, how are you?";
        // add first user
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO(senderUserName, "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
        // add second user
        final ContactDTO secondContactDTO = createContactDTO("Donnie", "Darko", "12345", "LA", "Fran Street");
        final UserDTO secondUserDTO = CreateUserDTO("donnie", "54321", recipientEmailAddress, secondContactDTO);
        final UserDTO secondResponse = userServiceBean.addUser(secondUserDTO);
        Assert.assertNotNull(secondResponse);
        // send message from first user to second user
        final MessageDTO messageDTOToSend = createMessageDTO(recipientEmailAddress, senderUserName, messageText);
        final MessageDTO messageResponse = messageServiceBean.sendMessage(messageDTOToSend);
        Assert.assertNotNull(messageResponse);
        // get all send messages of first user
        final List<MessageDTO> sendMessages = messageServiceBean.getAllSendMessagesOfUser(response.getUserId());
        Assert.assertEquals(1, sendMessages.size());
        final MessageDTO sendMessage = sendMessages.get(0);
        Assert.assertEquals(messageText, sendMessage.getMessage());
        // get all received message of second user
        final List<MessageDTO> receivedMessages = messageServiceBean.getAllReceivedMessagesOfUser(secondResponse
                .getUserId());
        Assert.assertEquals(1, receivedMessages.size());
        final MessageDTO receivedMessage = receivedMessages.get(0);
        Assert.assertEquals(messageText, receivedMessage.getMessage());
        // send and received message have to be equal
        Assert.assertEquals(sendMessage, receivedMessage);
    }

    @Test
    public void deleteReceivedMessageOfUserIT() {
        final String senderUserName = "tuxdevelop";
        final String recipientEmailAddress = "donnie@frank.org";
        final String messageText = "Hello Donnie, how are you?";
        // add first user
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO(senderUserName, "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
        // add second user
        final ContactDTO secondContactDTO = createContactDTO("Donnie", "Darko", "12345", "LA", "Fran Street");
        final UserDTO secondUserDTO = CreateUserDTO("donnie", "54321", recipientEmailAddress, secondContactDTO);
        final UserDTO secondResponse = userServiceBean.addUser(secondUserDTO);
        Assert.assertNotNull(secondResponse);
        // send message from first user to second user
        final MessageDTO messageDTOToSend = createMessageDTO(recipientEmailAddress, senderUserName, messageText);
        final MessageDTO messageResponse = messageServiceBean.sendMessage(messageDTOToSend);
        Assert.assertNotNull(messageResponse);
        // get all send messages of first user
        final List<MessageDTO> sendMessages = messageServiceBean.getAllSendMessagesOfUser(response.getUserId());
        Assert.assertEquals(1, sendMessages.size());
        final MessageDTO sendMessage = sendMessages.get(0);
        Assert.assertEquals(messageText, sendMessage.getMessage());
        // get all received message of second user
        final List<MessageDTO> receivedMessages = messageServiceBean.getAllReceivedMessagesOfUser(secondResponse
                .getUserId());
        Assert.assertEquals(1, receivedMessages.size());
        final MessageDTO receivedMessage = receivedMessages.get(0);
        Assert.assertEquals(messageText, receivedMessage.getMessage());
        // send and received message have to be equal
        Assert.assertEquals(sendMessage, receivedMessage);
        // delete received message of second user
        messageServiceBean.deleteReceivedMessageOfUser(receivedMessage.getMessageId(), secondResponse.getUserId());
        final List<MessageDTO> receivedMessagesAfterDeletion = messageServiceBean
                .getAllReceivedMessagesOfUser(secondResponse.getUserId());
        Assert.assertTrue(receivedMessagesAfterDeletion.isEmpty());
    }

    @Test
    public void deleteSendMessageOfUserIT() {
        final String senderUserName = "tuxdevelop";
        final String recipientEmailAddress = "donnie@frank.org";
        final String messageText = "Hello Donnie, how are you?";
        // add first user
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO(senderUserName, "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
        // add second user
        final ContactDTO secondContactDTO = createContactDTO("Donnie", "Darko", "12345", "LA", "Fran Street");
        final UserDTO secondUserDTO = CreateUserDTO("donnie", "54321", recipientEmailAddress, secondContactDTO);
        final UserDTO secondResponse = userServiceBean.addUser(secondUserDTO);
        Assert.assertNotNull(secondResponse);
        // send message from first user to second user
        final MessageDTO messageDTOToSend = createMessageDTO(recipientEmailAddress, senderUserName, messageText);
        final MessageDTO messageResponse = messageServiceBean.sendMessage(messageDTOToSend);
        Assert.assertNotNull(messageResponse);
        // get all send messages of first user
        final List<MessageDTO> sendMessages = messageServiceBean.getAllSendMessagesOfUser(response.getUserId());
        Assert.assertEquals(1, sendMessages.size());
        final MessageDTO sendMessage = sendMessages.get(0);
        Assert.assertEquals(messageText, sendMessage.getMessage());
        // get all received message of second user
        final List<MessageDTO> receivedMessages = messageServiceBean.getAllReceivedMessagesOfUser(secondResponse
                .getUserId());
        Assert.assertEquals(1, receivedMessages.size());
        final MessageDTO receivedMessage = receivedMessages.get(0);
        Assert.assertEquals(messageText, receivedMessage.getMessage());
        // send and received message have to be equal
        Assert.assertEquals(sendMessage, receivedMessage);
        // delete send message of first user
        messageServiceBean.deleteSendMessageOfUser(sendMessage.getMessageId(), response.getUserId());
        final List<MessageDTO> sendMessagesAfterDeletion = messageServiceBean.getAllSendMessagesOfUser(response
                .getUserId());
        Assert.assertTrue(sendMessagesAfterDeletion.isEmpty());
    }

    @Test
    public void deleteMessageIT() {
        final String senderUserName = "tuxdevelop";
        final String recipientEmailAddress = "donnie@frank.org";
        final String messageText = "Hello Donnie, how are you?";
        // add first user
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO(senderUserName, "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
        // add second user
        final ContactDTO secondContactDTO = createContactDTO("Donnie", "Darko", "12345", "LA", "Fran Street");
        final UserDTO secondUserDTO = CreateUserDTO("donnie", "54321", recipientEmailAddress, secondContactDTO);
        final UserDTO secondResponse = userServiceBean.addUser(secondUserDTO);
        Assert.assertNotNull(secondResponse);
        // send message from first user to second user
        final MessageDTO messageDTOToSend = createMessageDTO(recipientEmailAddress, senderUserName, messageText);
        final MessageDTO messageResponse = messageServiceBean.sendMessage(messageDTOToSend);
        Assert.assertNotNull(messageResponse);
        // get all send messages of first user
        final List<MessageDTO> sendMessages = messageServiceBean.getAllSendMessagesOfUser(response.getUserId());
        Assert.assertEquals(1, sendMessages.size());
        final MessageDTO sendMessage = sendMessages.get(0);
        Assert.assertEquals(messageText, sendMessage.getMessage());
        // get all received message of second user
        final List<MessageDTO> receivedMessages = messageServiceBean.getAllReceivedMessagesOfUser(secondResponse
                .getUserId());
        Assert.assertEquals(1, receivedMessages.size());
        final MessageDTO receivedMessage = receivedMessages.get(0);
        Assert.assertEquals(messageText, receivedMessage.getMessage());
        // send and received message have to be equal
        Assert.assertEquals(sendMessage, receivedMessage);
        // delete message
        messageServiceBean.deleteMessage(sendMessage.getMessageId());
        final List<MessageDTO> sendMessagesAfterDeletion = messageServiceBean.getAllSendMessagesOfUser(response
                .getUserId());
        Assert.assertTrue(sendMessagesAfterDeletion.isEmpty());
        final List<MessageDTO> receivedMessagesAfterDeletion = messageServiceBean
                .getAllReceivedMessagesOfUser(secondResponse.getUserId());
        Assert.assertTrue(receivedMessagesAfterDeletion.isEmpty());
        try {
            messageServiceBean.getMessageById(sendMessage.getMessageId());
            Assert.fail("Message should be deleted");
        } catch (final IllegalArgumentException e) {
            // everything OK
        }
    }

}
