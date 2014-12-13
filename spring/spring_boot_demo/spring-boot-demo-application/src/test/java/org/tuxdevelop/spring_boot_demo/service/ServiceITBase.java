package org.tuxdevelop.spring_boot_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuxdevelop.spring_boot_demo.repository.RoleRepository;
import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

public abstract class ServiceITBase {

    @Autowired
    private UserServiceBean userServiceBean;

    @Autowired
    private MessageServiceBean messageServiceBean;

    @Autowired
    private RoleRepository roleRepository;

    protected UserDTO CreateUserDTO(final String userName, final String password, final String emailAddress,
                                    final ContactDTO contactDTO) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setContactDTO(contactDTO);
        userDTO.setEmailAddress(emailAddress);
        userDTO.setPassword(password);
        userDTO.setUserName(userName);
        return userDTO;
    }

    protected ContactDTO createContactDTO(final String firstName, final String lastName, final String zipCode,
                                          final String city, final String streetLine) {
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setCity(city);
        contactDTO.setFirstName(firstName);
        contactDTO.setLastName(lastName);
        contactDTO.setStreetLine(streetLine);
        contactDTO.setZipCode(zipCode);
        return contactDTO;
    }

    protected MessageDTO createMessageDTO(final String emailAddress, final String senderUserName,
                                          final String message) {
        final MessageDTO messageDTO = new MessageDTO();
        messageDTO.setEmailAddress(emailAddress);
        messageDTO.setSenderUserName(senderUserName);
        messageDTO.setMessage(message);
        return messageDTO;
    }

}
