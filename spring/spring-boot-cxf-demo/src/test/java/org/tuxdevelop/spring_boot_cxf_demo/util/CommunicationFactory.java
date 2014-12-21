package org.tuxdevelop.spring_boot_cxf_demo.util;

import org.tuxdevelop.spring_boot_cxf_demo.domain.CommunicationClassifier;
import org.tuxdevelop.spring_boot_cxf_demo.domain.CommunicationType;
import org.tuxdevelop.spring_boot_cxf_demo.domain.EmailCommunication;

public class CommunicationFactory {

	public static EmailCommunication createEmailCommunication(final CommunicationClassifier classifier) {
		final EmailCommunication emailCommunication = new EmailCommunication();
		emailCommunication.setCommunicationType(CommunicationType.EMAIL);
		emailCommunication.setCommunicationClassifier(classifier);
		emailCommunication.setEmailAddress("junit@test.org");
		return GenericFactory.attachGenericValues(emailCommunication);
	}

}
