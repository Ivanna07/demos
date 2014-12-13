package org.tuxdevelop.spring_boot_demo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_demo.persistence.domain.Contact;
import org.tuxdevelop.spring_boot_demo.persistence.domain.User;
import org.tuxdevelop.spring_boot_demo.configuration.CommonITConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommonITConfiguration.class)
public class UserRepositoryIT extends RepositoryITBase {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void saveUserIT() {
		final Contact contact = addContact("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
		final User user = User.buildUser("tuxdevelop", "12345", "tuxdevelop@gmail.com", contact);
		final User savedUser = userRepository.save(user);
		Assert.assertNotNull(savedUser);
	}

	@Test
	public void findByEmailAddressIT() {
		final String emailAddress = "tuxdeveloptoFind@gmail.com";
		final Contact contact = addContact("Marcel", "Becker", "56076", "Koblenz", "Am Kratzkopfer Hof 1");
		addUser("tuxdevelop", "12345", emailAddress, contact);
		final User user = userRepository.findByEmailAddress(emailAddress);
		Assert.assertNotNull(user);
		Assert.assertEquals(emailAddress, user.getEmailAddress());
	}
}
