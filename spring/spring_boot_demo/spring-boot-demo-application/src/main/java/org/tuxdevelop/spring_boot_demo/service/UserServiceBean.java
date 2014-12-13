package org.tuxdevelop.spring_boot_demo.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_boot_demo.persistence.domain.Contact;
import org.tuxdevelop.spring_boot_demo.persistence.domain.User;
import org.tuxdevelop.spring_boot_demo.persistence.domain.UserRole;
import org.tuxdevelop.spring_boot_demo.repository.ContactRepository;
import org.tuxdevelop.spring_boot_demo.repository.RoleRepository;
import org.tuxdevelop.spring_boot_demo.repository.UserRepository;
import org.tuxdevelop.spring_boot_demo.security.constants.RoleConstants;
import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

@Slf4j
@Component
public class UserServiceBean {

	@Autowired
	private ContactServiceBean contactServiceBean;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * @param userDTO
	 * @return
	 */
	public UserDTO addUser(final UserDTO userDTO) {
		final Boolean emailAddressExists = emailAddressExists(userDTO.getEmailAddress());
		final Boolean userNameExists = userNameExists(userDTO.getUserName());
		if (!emailAddressExists && !userNameExists) {
			final ContactDTO contactDTO = userDTO.getContactDTO();
			final Long contactId = contactServiceBean.addContact(contactDTO);
			final Contact contact = contactRepository.findOne(contactId);
			final User user = User.buildUser(userDTO.getUserName(), userDTO.getPassword(), userDTO.getEmailAddress(),
					contact);
			final UserRole role = new UserRole();
			role.setRole(RoleConstants.ROLE_USER);
			role.setUserName(userDTO.getUserName());
			roleRepository.save(role);
			final UserRole writeRole = new UserRole();
			writeRole.setRole(RoleConstants.ROLE_WRITE);
			writeRole.setUserName(userDTO.getUserName());
			roleRepository.save(writeRole);
			return userRepository.save(user).toDTO();
		} else {
			final String message = "user name: " + userDTO.getUserName() + " or email address: "
					+ userDTO.getEmailAddress() + " already exists";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * @param userName
	 * @return
	 */
	@PreAuthorize("(hasRole('" + RoleConstants.ROLE_USER + "') AND #userName == authentication.name) OR hasRole('"
			+ RoleConstants.ROLE_ADMIN + "')")
	public UserDTO getUserByUserName(final String userName) {
		final User user = userRepository.findByUserName(userName);
		if (user != null) {
			return user.toDTO();
		} else {
			throw new IllegalArgumentException("No user found for user name: " + userName);
		}
	}

	/**
	 * @param userId
	 * @return
	 */
	public UserDTO getUserById(final Long userId) {
		final User user = userRepository.findOne(userId);
		if (user == null) {
			throw new IllegalAccessError("user not found for userId: " + userId);
		}
		return user.toDTO();
	}

	/**
	 * @param emailAddress
	 * @return
	 */
	public Boolean emailAddressExists(final String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress) != null;
	}

	/**
	 * @param userName
	 * @return
	 */
	public Boolean userNameExists(final String userName) {
		return userRepository.findByUserName(userName) != null;
	}

	/**
	 * @param userDTO
	 */
	@PreAuthorize("(hasRole('" + RoleConstants.ROLE_WRITE
			+ "') AND #userDTO.userName == authentication.name) OR hasRole('" + RoleConstants.ROLE_ADMIN + "')")
	public void updateUser(final UserDTO userDTO) {
		final User user = userRepository.findByUserName(userDTO.getUserName());
		mergeUser(user, userDTO);
		userRepository.save(user);
	}

	/**
	 * @param userName
	 */
	@PreAuthorize("hasRole('" + RoleConstants.ROLE_ADMIN + "') OR #userName == authentication.name")
	public void deleteUser(final String userName) {
		final User user = userRepository.findByUserName(userName);
		userRepository.delete(user);
	}

	private void mergeUser(final User user, final UserDTO userDTO) {
		user.setEmailAddress(userDTO.getEmailAddress());
		user.getContact().setFirstName(userDTO.getContactDTO().getFirstName());
		user.getContact().setLastName(userDTO.getContactDTO().getLastName());
		user.getContact().setZipCode(userDTO.getContactDTO().getZipCode());
		user.getContact().setCity(userDTO.getContactDTO().getCity());
		user.getContact().setStreetLine(userDTO.getContactDTO().getStreetLine());
		user.getContact().setCity(userDTO.getContactDTO().getCity());
	}

}
