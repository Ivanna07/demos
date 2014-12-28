package org.tuxdevelop.spring_boot_demo.soap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_demo.configuration.SoapITConfiguration;
import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;
import org.tuxdevelop.spring_boot_demo.service.intf.UserService;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoapITConfiguration.class)
public class UserServiceSoapIT {

    @Autowired
    private UserService userServiceSoap;

    private static final String TEST_EMAIL_ADDRESS = "test@junit.de";
    private static final String TEST_USER_NAME = "test1";
    private static final String TEST_UPDATE_USER_FIRST_NAME = "updated";
    private static final String USER_NAME = "root";

    @Test
    public void getUserIT() {
        final UserDTO userDTO = userServiceSoap.getUser(USER_NAME);
        Assert.assertNotNull(userDTO);
        log.info("got user: " + userDTO);
    }

    @Test
    public void addUserIT() {
        final UserDTO userDTO = createUser(TEST_USER_NAME, TEST_USER_NAME, TEST_EMAIL_ADDRESS);
        final UserDTO response = userServiceSoap.addUser(userDTO);
        final UserDTO gotUser = userServiceSoap.getUser(TEST_USER_NAME);
        Assert.assertEquals(response, gotUser);
        userServiceSoap.deleteUser(TEST_USER_NAME);
    }

    @Test
    public void UpdateUserIT() {
        final UserDTO userDTO = createUser(TEST_USER_NAME, TEST_USER_NAME, TEST_EMAIL_ADDRESS);
        final UserDTO response = userServiceSoap.addUser(userDTO);
        final UserDTO gotUser = userServiceSoap.getUser(TEST_USER_NAME);
        Assert.assertEquals(response, gotUser);
        gotUser.getContactDTO().setFirstName(TEST_UPDATE_USER_FIRST_NAME);
        userServiceSoap.updateUser(gotUser);
        final UserDTO updatedUser = userServiceSoap.getUser(TEST_USER_NAME);
        Assert.assertEquals(gotUser, updatedUser);
        userServiceSoap.deleteUser(TEST_USER_NAME);
    }

    @Test
    public void deleteUserIT() {
        final UserDTO userDTO = createUser(TEST_USER_NAME, TEST_USER_NAME, TEST_EMAIL_ADDRESS);
        final UserDTO response = userServiceSoap.addUser(userDTO);
        userServiceSoap.deleteUser(response.getUserName());
        try {
            userServiceSoap.getUser(response.getUserName());
            Assert.fail();
        } catch (final JaxWsSoapFaultException e) {
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
