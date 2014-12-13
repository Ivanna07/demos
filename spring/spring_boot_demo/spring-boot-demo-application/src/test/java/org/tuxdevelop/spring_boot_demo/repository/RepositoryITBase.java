package org.tuxdevelop.spring_boot_demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuxdevelop.spring_boot_demo.persistence.domain.Contact;
import org.tuxdevelop.spring_boot_demo.persistence.domain.User;

public abstract class RepositoryITBase {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	protected User addUser(final String userName, final String password, final String emailAddress,
			final Contact contact) {
		final User user = User.buildUser(userName, password, emailAddress, contact);
		return userRepository.save(user);
	}

	protected Contact addContact(final String firstName, final String lastName, final String zipCode,
			final String city, final String streetLine) {
		final Contact contact = Contact.buildContact(firstName, lastName, zipCode, city, streetLine);
		return contactRepository.save(contact);
	}

}
