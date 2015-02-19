package org.tuxdevelop.spring_boot_demo.persistence.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "user")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "email_address")
	private String emailAddress;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Contact contact;
	@OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
	private Collection<Message> receivedMessages;
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private Collection<Message> sendMessages;

	public UserDTO toDTO() {
		final UserDTO dto = new UserDTO();
		dto.setEmailAddress(emailAddress);
		dto.setUserName(userName);
		dto.setPassword(password);
		dto.setUserId(super.getId());
		if (contact != null) {
			dto.setContactDTO(contact.toDTO());
		}
		return dto;
	}

	public static User buildUser(final String userName, final String password, final String emailAddress,
			final Contact contact) {
		final User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmailAddress(emailAddress);
		user.setContact(contact);
		return user;
	}

}
