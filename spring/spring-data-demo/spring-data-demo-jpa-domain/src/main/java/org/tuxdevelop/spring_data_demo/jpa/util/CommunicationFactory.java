package org.tuxdevelop.spring_data_demo.jpa.util;

import org.tuxdevelop.spring_data_demo.jpa.domain.CommunicationClassifier;
import org.tuxdevelop.spring_data_demo.jpa.domain.CommunicationType;
import org.tuxdevelop.spring_data_demo.jpa.domain.EmailCommunication;
import org.tuxdevelop.spring_data_demo.jpa.domain.PhoneCommunication;

public class CommunicationFactory {

	public static EmailCommunication createEmailCommunication(final CommunicationClassifier classifier) {
		final EmailCommunication emailCommunication = new EmailCommunication();
		emailCommunication.setCommunicationType(CommunicationType.EMAIL);
		emailCommunication.setCommunicationClassifier(classifier);
		emailCommunication.setEmailAddress("junit@test.org");
		return GenericFactory.attachGenericValues(emailCommunication);
	}

	public static PhoneCommunication createPhoneCommunication(final CommunicationClassifier classifier) {
		final PhoneCommunication phoneCommunication = new PhoneCommunication();
		phoneCommunication.setCommunicationType(CommunicationType.PHONE);
		phoneCommunication.setCommunicationClassifier(classifier);
		phoneCommunication.setAreaCode("12345");
		phoneCommunication.setCountryCode("+49");
		phoneCommunication.setNumber("987654");
		return GenericFactory.attachGenericValues(phoneCommunication);
	}

}
