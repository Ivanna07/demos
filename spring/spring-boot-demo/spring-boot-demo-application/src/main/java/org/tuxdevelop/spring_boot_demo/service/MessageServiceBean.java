package org.tuxdevelop.spring_boot_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_boot_demo.persistence.domain.Message;
import org.tuxdevelop.spring_boot_demo.persistence.domain.User;
import org.tuxdevelop.spring_boot_demo.repository.MessageRepository;
import org.tuxdevelop.spring_boot_demo.repository.UserRepository;
import org.tuxdevelop.spring_boot_demo.security.constants.RoleConstants;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Transactional
@Component
public class MessageServiceBean {

    @Autowired
    private UserServiceBean userServiceBean;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    /**
     * @param messageDTO
     * @return
     */
    @PreAuthorize("hasRole('" + RoleConstants.ROLE_USER + "') AND #messageDTO.senderUserName == authentication.name")
    public MessageDTO sendMessage(final MessageDTO messageDTO) {
        validateForSendMessage(messageDTO);
        final Long messageId = attacheMessage(messageDTO.getEmailAddress(), messageDTO.getSenderUserName(),
                messageDTO.getMessage());
        messageDTO.setMessageId(messageId);
        return messageDTO;
    }

    /**
     * @param messageId
     * @param status
     */
    public void updateMessageStatus(final Long messageId, final Boolean status) {
        final Message message = messageRepository.findOne(messageId);
        if (message == null) {
            throw new IllegalArgumentException("No message found for messageId: " + messageId);
        }
        message.setRead(status);
        messageRepository.save(message);
    }

    /**
     * @param messageId
     * @param userId
     */
    public void deleteReceivedMessageOfUser(final Long messageId, final Long userId) {
        final User user = userRepository.findOne(userId);
        Message messageToDelete = null;
        for (final Message message : user.getReceivedMessages()) {
            if (messageId.equals(message.getId())) {
                messageToDelete = message;
                break;
            }
        }
        if (messageToDelete != null) {
            user.getReceivedMessages().remove(messageToDelete);
            userRepository.save(user);
        }
    }

    /**
     * @param messageId
     * @param userId
     */
    public void deleteSendMessageOfUser(final Long messageId, final Long userId) {
        final User user = userRepository.findOne(userId);
        Message messageToDelete = null;
        for (final Message message : user.getSendMessages()) {
            if (messageId.equals(message.getId())) {
                messageToDelete = message;
                break;
            }
        }
        if (messageToDelete != null) {
            user.getSendMessages().remove(messageToDelete);
            userRepository.save(user);
        }
    }

    /**
     * @param messageId
     */
    public void deleteMessage(final Long messageId) {
        deleteMessageOfAllUser(messageId);
        messageRepository.delete(messageId);
    }

    /**
     * @param userName
     * @return
     */
    @PreAuthorize("hasRole('" + RoleConstants.ROLE_USER + "') AND #userName == authentication.name")
    public List<MessageDTO> getAllReceivedMessagesByUserName(final String userName) {
        final User user = userRepository.findByUserName(userName);
        return getAllReceivedEmails(user);
    }

    /**
     * @param userName
     * @return
     */
    @PreAuthorize("hasRole('" + RoleConstants.ROLE_USER + "') AND #userName == authentication.name")
    public List<MessageDTO> getAllSendMessagesByUserName(final String userName) {
        final User user = userRepository.findByUserName(userName);
        return getAllSendMessages(user);
    }

    /**
     * @param userId
     * @return
     */
    @PreAuthorize("hasRole('" + RoleConstants.ROLE_USER + "')")
    public List<MessageDTO> getAllReceivedMessagesOfUser(final Long userId) {
        final User user = userRepository.getOne(userId);
        return getAllReceivedEmails(user);
    }

    /**
     * @param userId
     * @return
     */
    @PreAuthorize("hasRole('" + RoleConstants.ROLE_USER + "')")
    public List<MessageDTO> getAllSendMessagesOfUser(final Long userId) {
        final User user = userRepository.getOne(userId);
        return getAllSendMessages(user);
    }

    /**
     * @param messageId
     * @return
     */
    public MessageDTO getMessageById(final Long messageId) {
        final Message message = messageRepository.findOne(messageId);
        if (message == null) {
            throw new IllegalArgumentException("no message found for messageId:" + messageId);
        }
        return message.toDTO();
    }

    private void validateForSendMessage(final MessageDTO messageDTO) {
        if (messageDTO == null) {
            throw new IllegalArgumentException("messageDTO may not be null!");
        }
        final Boolean recipientExists = userServiceBean.emailAddressExists(messageDTO.getEmailAddress());
        final Boolean senderExists = userServiceBean.userNameExists(messageDTO.getSenderUserName());
        if (!recipientExists) {
            throw new IllegalArgumentException("email Address :" + messageDTO.getEmailAddress() + " does not exist!");
        }
        if (!senderExists) {
            throw new IllegalArgumentException("Invalid user name: " + messageDTO.getSenderUserName());
        }
    }

    private Long attacheMessage(final String emailAddress, final String senderUserName, final String messageText) {
        final User user = userRepository.findByEmailAddress(emailAddress);
        final User sender = userRepository.findByUserName(senderUserName);
        final String senderEmaiAddress = sender.getEmailAddress();
        final Message message = Message.buildMessage(senderEmaiAddress, messageText, Boolean.FALSE, user, sender);
        final Message savedMessage = messageRepository.save(message);
        if (user.getReceivedMessages() == null) {
            user.setReceivedMessages(new LinkedList<Message>());
        }
        user.getReceivedMessages().add(message);
        userRepository.save(user);
        if (sender.getSendMessages() == null) {
            sender.setSendMessages(new LinkedList<Message>());
        }
        sender.getSendMessages().add(message);
        userRepository.save(sender);
        return savedMessage.getId();
    }

    private void deleteMessageOfAllUser(final Long messageId) {
        final Message message = messageRepository.findOne(messageId);
        final User sender = message.getSender();
        final User recipient = message.getRecipient();
        deleteReceivedMessageOfUser(message.getId(), recipient.getId());
        deleteSendMessageOfUser(message.getId(), sender.getId());
    }

    private List<MessageDTO> getAllReceivedEmails(final User user) {
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        final List<MessageDTO> messageDTOs = new LinkedList<MessageDTO>();
        for (final Message message : user.getReceivedMessages()) {
            final MessageDTO messageDTO = message.toDTO();
            messageDTOs.add(messageDTO);
        }
        return messageDTOs;
    }

    private List<MessageDTO> getAllSendMessages(final User user) {
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        final List<MessageDTO> messageDTOs = new LinkedList<MessageDTO>();
        for (final Message message : user.getSendMessages()) {
            final MessageDTO messageDTO = message.toDTO();
            messageDTOs.add(messageDTO);
        }
        return messageDTOs;
    }

}
