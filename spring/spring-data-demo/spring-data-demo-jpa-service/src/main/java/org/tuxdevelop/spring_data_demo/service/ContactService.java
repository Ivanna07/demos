package org.tuxdevelop.spring_data_demo.service;

import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;

import java.util.Collection;

public interface ContactService {

	Contact getContact(final Long id);

	Contact addContact(final Contact contact);

	Collection<Contact> addContacts(final Collection<Contact> contacts);

	Contact updateContact(final Long id, final Contact contact);

	Collection<Contact> updateContacts(final Collection<Contact> contacts);

	void deleteContact(final Long id);

    Contact getStandardContactOfCustomer(final Long customerId);
}
