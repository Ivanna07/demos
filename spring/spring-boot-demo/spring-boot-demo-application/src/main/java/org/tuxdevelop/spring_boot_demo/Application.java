package org.tuxdevelop.spring_boot_demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.tuxdevelop.spring_boot_demo.persistence.domain.Message;
import org.tuxdevelop.spring_boot_demo.persistence.domain.User;
import org.tuxdevelop.spring_boot_demo.persistence.domain.UserRole;
import org.tuxdevelop.spring_boot_demo.repository.MessageRepository;
import org.tuxdevelop.spring_boot_demo.repository.RoleRepository;
import org.tuxdevelop.spring_boot_demo.repository.UserRepository;
import org.tuxdevelop.spring_boot_demo.security.constants.RoleConstants;
import org.tuxdevelop.spring_boot_demo.service.UserServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages = {"org.tuxdevelop.spring_boot_demo.repository"})
@EnableAutoConfiguration
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public InitializingBean populateStaticData(final UserServiceBean userServiceBean,
                                               final RoleRepository roleRepository,
                                               final MessageRepository messageRepository,
                                               final UserRepository userRepository) {
        return new InitializingBean() {
            @Override
            public void afterPropertiesSet() throws Exception {
                final ContactDTO contactDTO = new ContactDTO();
                contactDTO.setCity("root town");
                contactDTO.setFirstName("root");
                contactDTO.setLastName("");
                contactDTO.setStreetLine("root street");
                contactDTO.setZipCode("98765");
                final UserDTO userDTO = new UserDTO();
                userDTO.setUserName("root");
                userDTO.setPassword("root");
                userDTO.setEmailAddress("root@spring.boot");
                userDTO.setContactDTO(contactDTO);
                userServiceBean.addUser(userDTO);
                final UserRole roleAdmin = new UserRole();
                roleAdmin.setRole(RoleConstants.ROLE_ADMIN);
                roleAdmin.setUserName("root");
                roleRepository.save(roleAdmin);
                final UserRole roleRead = new UserRole();
                roleRead.setRole(RoleConstants.ROLE_USER);
                roleRead.setUserName("root");
                roleRepository.save(roleRead);
                final User user = userRepository.findByUserName("root");
                final Message message = new Message();
                message.setMessageText("Hello root!");
                message.setRecipient(user);
                message.setSender(user);
                message.setSenderAddress("root@spring.boot");
                message.setRead(Boolean.FALSE);
                messageRepository.save(message);
            }
        };
    }
}
