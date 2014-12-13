package org.tuxdevelop.spring_boot_demo.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "message")
public class Message extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "sender_address")
	private String senderAddress;
	@Column(name = "message_text")
	private String messageText;
	@Column(name = "read")
	private Boolean read;
	@ManyToOne
	@PrimaryKeyJoinColumn
	private User recipient;
	@ManyToOne
	@PrimaryKeyJoinColumn
	private User sender;

	public MessageDTO toDTO() {
		final MessageDTO dto = new MessageDTO();
		dto.setEmailAddress(recipient.getEmailAddress());
		dto.setMessage(messageText);
		dto.setSenderUserName(sender.getUserName());
		dto.setRead(read);
		dto.setMessageId(super.getId());
		return dto;
	}

	public static Message buildMessage(final String senderAddress, final String messageText, final Boolean read,
			final User recipient, final User sender) {
		final Message message = new Message();
		message.setMessageText(messageText);
		message.setRead(read);
		message.setRecipient(recipient);
		message.setSender(sender);
		message.setSenderAddress(senderAddress);
		return message;
	}
}
