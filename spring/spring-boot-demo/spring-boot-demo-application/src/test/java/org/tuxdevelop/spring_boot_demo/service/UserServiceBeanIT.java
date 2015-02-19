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
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommonITConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceBeanIT extends ServiceITBase {

    @Autowired
    private org.tuxdevelop.spring_boot_demo.service.UserServiceBean userServiceBean;

    @Test
    public void addUserIT() {
        //authenticate("tuxdevelop", "tuxdevelop");
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO("tuxdevelop", "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserUserNameExistsIT() {
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO("tuxdevelop", "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
        final UserDTO secondUserDTO = CreateUserDTO("tuxdevelop", "12345", "tuxdevelop2@gmail.com", contactDTO);
        userServiceBean.addUser(secondUserDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserEmailAddressExistsIT() {
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO("tuxdevelop", "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
        final UserDTO secondUserDTO = CreateUserDTO("tuxdevelop2", "12345", "tuxdevelop@gmail.com", contactDTO);
        userServiceBean.addUser(secondUserDTO);
    }

    @Test
    public void getUserByUserNameIT() {
        final String userName = "tuxdevelop";
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO(userName, "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
        final UserDTO gotUserDTO = userServiceBean.getUserByUserName(userName);
        Assert.assertNotNull(gotUserDTO);
    }

    @Test
    public void updateUserIT() {
        final String newEmailAddress = "changed@test.tux";
        final String changedLastName = "Darko";
        final String userName = "tuxdevelop";
        final ContactDTO contactDTO = createContactDTO("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
        final UserDTO userDTO = CreateUserDTO(userName, "12345", "tuxdevelop@gmail.com", contactDTO);
        final UserDTO response = userServiceBean.addUser(userDTO);
        Assert.assertNotNull(response);
        final UserDTO gotUserDTO = userServiceBean.getUserByUserName(userName);
        Assert.assertNotNull(gotUserDTO);
        gotUserDTO.setEmailAddress(newEmailAddress);
        gotUserDTO.getContactDTO().setLastName(changedLastName);
        userServiceBean.updateUser(gotUserDTO);
        final UserDTO changedUser = userServiceBean.getUserByUserName(userName);
        Assert.assertNotNull(changedUser);
        Assert.assertEquals(newEmailAddress, changedUser.getEmailAddress());
        Assert.assertEquals(changedLastName, changedUser.getContactDTO().getLastName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByUserNameNotExistsIT() {
        final String userName = "Donnie";
        userServiceBean.getUserByUserName(userName);
    }

}
