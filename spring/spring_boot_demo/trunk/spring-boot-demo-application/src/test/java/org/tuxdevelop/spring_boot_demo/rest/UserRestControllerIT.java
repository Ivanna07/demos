package org.tuxdevelop.spring_boot_demo.rest;

import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.tuxdevelop.spring_boot_demo.configuration.RestITConfiguration;
import org.tuxdevelop.spring_boot_demo.service.client.UserServiceRestClient;
import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestITConfiguration.class)
public class UserRestControllerIT {

	private static final String TEST_EMAIL_ADDRESS = "test@junit.de";
	private static final String TEST_USER_NAME = "test1";
	private static final String TEST_UPDATE_USER_FIRST_NAME = "updated";
	private static final String USER_NAME = "root";

	@Autowired
	private UserServiceRestClient userServiceRestClient;

	@Test
	public void getUserIT() {
		final UserDTO userDTO = userServiceRestClient.getUser(USER_NAME);
		Assert.assertNotNull(userDTO);
		log.info("got user: " + userDTO);
	}

	@Test
	public void addUserIT() {
		final UserDTO userDTO = createUser(TEST_USER_NAME, TEST_USER_NAME, TEST_EMAIL_ADDRESS);
		final UserDTO response = userServiceRestClient.addUser(userDTO);
		final UserDTO gotUser = userServiceRestClient.getUser(TEST_USER_NAME);
		Assert.assertEquals(response, gotUser);
		userServiceRestClient.deleteUser(TEST_USER_NAME);
	}

	@Test
	public void UpdateUserIT() {
		final UserDTO userDTO = createUser(TEST_USER_NAME, TEST_USER_NAME, TEST_EMAIL_ADDRESS);
		final UserDTO response = userServiceRestClient.addUser(userDTO);
		final UserDTO gotUser = userServiceRestClient.getUser(TEST_USER_NAME);
		Assert.assertEquals(response, gotUser);
		gotUser.getContactDTO().setFirstName(TEST_UPDATE_USER_FIRST_NAME);
		userServiceRestClient.updateUser(gotUser);
		final UserDTO updatedUser = userServiceRestClient.getUser(TEST_USER_NAME);
		Assert.assertEquals(gotUser, updatedUser);
		userServiceRestClient.deleteUser(TEST_USER_NAME);
	}

	@Test
	public void deleteUserIT() {
		final UserDTO userDTO = createUser(TEST_USER_NAME, TEST_USER_NAME, TEST_EMAIL_ADDRESS);
		final UserDTO response = userServiceRestClient.addUser(userDTO);
		userServiceRestClient.deleteUser(response.getUserName());
		try {
			userServiceRestClient.getUser(response.getUserName());
			Assert.fail();
		} catch (final HttpServerErrorException e) {
			log.info("caught exception: " + e.getClass().getName() + " everything ok");
		}
	}

	private UserDTO createUser(final String userName, final String password, final String emailAddress) {
		final ContactDTO contactDTO = createContactDTO();
		final UserDTO userDTO = new UserDTO();
		userDTO.setContactDTO(contactDTO);
		userDTO.setUserName(userName);
		userDTO.setPassword(password);
		userDTO.setEmailAddress(emailAddress);
		return userDTO;
	}

	private ContactDTO createContactDTO() {
		final ContactDTO contactDTO = new ContactDTO();
		contactDTO.setFirstName("addedByJunit");
		contactDTO.setLastName("addedByJunit");
		contactDTO.setStreetLine("JUnitStreet");
		contactDTO.setZipCode("87654");
		contactDTO.setCity("SpringStreet");
		return contactDTO;
	}

}
