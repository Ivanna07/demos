package org.tuxdevelop.spring_data_demo.util;

import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;
import org.tuxdevelop.spring_data_demo.jpa.domain.ContactClassifier;
import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;

import java.util.Collection;
import java.util.LinkedList;

public class CustomerFactory {

	public static Customer createCustomer() {
		final Customer customer = new Customer();
		final Collection<Contact> contacts = new LinkedList<>();
		final Contact contact = ContactFactory.createContact(ContactClassifier.STANDARD);
		contacts.add(contact);
		customer.setContacts(contacts);
		return GenericFactory.attachGenericValues(customer);
	}

}
