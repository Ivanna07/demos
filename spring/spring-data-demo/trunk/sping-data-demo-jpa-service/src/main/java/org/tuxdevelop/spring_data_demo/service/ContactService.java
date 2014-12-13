package org.tuxdevelop.spring_data_demo.service;

import java.util.Collection;

import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;

public interface ContactService {

	Contact getContact(final Long id);

	Contact addContact(final Contact contact);

	Collection<Contact> addContacts(final Collection<Contact> contacts);

	Contact updateContact(final Long id, final Contact contact);

	Collection<Contact> updateContacts(final Collection<Contact> contacts);

	void deleteContact(final Long id);
}
