package org.tuxdevelop.spring_boot_cxf_demo.util;

import org.tuxdevelop.spring_boot_cxf_demo.domain.*;

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
		final Collection<EmailCommunication> emailCommunications = new LinkedList<>();
		final EmailCommunication emailCommunication = CommunicationFactory
				.createEmailCommunication(CommunicationClassifier.STANDARD);
		emailCommunications.add(emailCommunication);
		contact.setEmailCommunications(emailCommunications);
		return GenericFactory.attachGenericValues(contact);
	}

}
