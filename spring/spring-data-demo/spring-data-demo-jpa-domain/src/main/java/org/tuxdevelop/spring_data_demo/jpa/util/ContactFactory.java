package org.tuxdevelop.spring_data_demo.jpa.util;

import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;
import org.tuxdevelop.spring_data_demo.jpa.domain.ContactClassifier;

public class ContactFactory {

	public static Contact createContact(final ContactClassifier classifier) {
		final Contact contact = new Contact();
		contact.setContactClassifier(classifier);
		contact.setFirstName("tux");
		contact.setLastName("pinguin");
		contact.setStreetLine("tux street 1");
		contact.setZipCode("92843");
		contact.setCity("pinguin town");
		return GenericFactory.attachGenericValues(contact);
	}

}
