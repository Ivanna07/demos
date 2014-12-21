package org.tuxdevelop.spring_boot_cxf_demo.util;

import org.tuxdevelop.spring_boot_cxf_demo.domain.CommunicationClassifier;
import org.tuxdevelop.spring_boot_cxf_demo.domain.CommunicationType;
import org.tuxdevelop.spring_boot_cxf_demo.domain.EmailCommunication;
import org.tuxdevelop.spring_boot_cxf_demo.domain.PhoneCommunication;

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
