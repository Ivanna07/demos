package org.tuxdevelop.spring_data_demo.util;

import org.tuxdevelop.spring_data_demo.jpa.domain.Communication;
import org.tuxdevelop.spring_data_demo.jpa.domain.CommunicationClassifier;
import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;
import org.tuxdevelop.spring_data_demo.jpa.domain.ContactClassifier;

import java.util.Collection;
import java.util.LinkedList;

public class ContactFactory {

	public static Contact createContact(final ContactClassifier classifier) {
		final Contact contact = new Contact();
		contact.setContactClassifier(classifier);
		contact.setFirstName("tux");
		contact.setLastName("pinguin");
		contact.setStreetLine("tux street 1");
		contact.setZipCode("92843");
		contact.setCity("pinguin town");
		final Collection<Communication> emailCommunications = new LinkedList<>();
		final Collection<Communication> phoneCommunications = new LinkedList<>();
		final Communication emailCommunication = CommunicationFactory
				.createEmailCommunication(CommunicationClassifier.STANDARD);
		final Communication phoneCommunication = CommunicationFactory
				.createPhoneCommunication(CommunicationClassifier.STANDARD);
		emailCommunications.add(emailCommunication);
		phoneCommunications.add(phoneCommunication);
		contact.setEmailCommunications(emailCommunications);
		contact.setPhoneCommunications(phoneCommunications);
		return GenericFactory.attachGenericValues(contact);
	}

}
