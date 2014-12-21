package org.tuxdevelop.spring_boot_cxf_demo.service;


import org.tuxdevelop.spring_boot_cxf_demo.domain.Contact;

import javax.jws.WebService;
import java.util.Collection;

@WebService(targetNamespace = "org.tuxdevelop.spring_boot_cxf_demo")
public interface ContactService {

	Contact getContact(final Long id);

	Contact addContact(final Contact contact);

	Collection<Contact> addContacts(final Collection<Contact> contacts);

	Contact updateContact(final Long id, final Contact contact);

	Collection<Contact> updateContacts(final Collection<Contact> contacts);

	void deleteContact(final Long id);

    Contact getStandardContactOfCustomer(final Long customerId);
}
