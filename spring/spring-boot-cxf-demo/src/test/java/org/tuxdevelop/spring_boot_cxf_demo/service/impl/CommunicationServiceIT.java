package org.tuxdevelop.spring_boot_cxf_demo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_cxf_demo.domain.CommunicationClassifier;
import org.tuxdevelop.spring_boot_cxf_demo.domain.EmailCommunication;
import org.tuxdevelop.spring_boot_cxf_demo.it_configuration.ITConfiguration;
import org.tuxdevelop.spring_boot_cxf_demo.service.CommunicationService;
import org.tuxdevelop.spring_boot_cxf_demo.util.CommunicationFactory;

import java.util.Collection;
import java.util.LinkedList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CommunicationServiceIT {

	@Autowired
	private CommunicationService communicationServiceBean;

	@Test
	public void addCommunicationEmailCommunicationIT() {
		final EmailCommunication emailCommunication = CommunicationFactory
				.createEmailCommunication(CommunicationClassifier.STANDARD);
		final EmailCommunication savedEmailCommunication = communicationServiceBean.addCommunication(emailCommunication);
		Assert.assertNotNull(savedEmailCommunication);
		System.err.println(savedEmailCommunication);
	}

	@Test
	public void addCommunicationsIT() {
		final EmailCommunication standardEmailCommunication = CommunicationFactory
				.createEmailCommunication(CommunicationClassifier.STANDARD);
		final EmailCommunication emailCommunication = CommunicationFactory
				.createEmailCommunication(CommunicationClassifier.ADDITIONAL);
		final Collection<EmailCommunication> communications = new LinkedList<>();
		communications.add(emailCommunication);
		communications.add(standardEmailCommunication);
		final Collection<EmailCommunication> addedCommunications = communicationServiceBean.addCommunications(communications);
		Assert.assertNotNull(addedCommunications);
		Assert.assertFalse(addedCommunications.isEmpty());
		for (final EmailCommunication communication : addedCommunications) {
			System.err.println(communication);
		}
	}

	@Test
	public void updateCommunicationIT() {
		final String NEW_EMAIL = "test@updated.com";
		final EmailCommunication emailCommunication = CommunicationFactory
				.createEmailCommunication(CommunicationClassifier.STANDARD);
		final EmailCommunication savedEmailCommunication = communicationServiceBean.addCommunication(emailCommunication);
		(savedEmailCommunication).setEmailAddress(NEW_EMAIL);
		final EmailCommunication updatedCommunication = communicationServiceBean.updateCommunication(
                savedEmailCommunication.getId(), savedEmailCommunication);
		Assert.assertNotNull(updatedCommunication);
		final String updatedEmailAddress = updatedCommunication.getEmailAddress();
		Assert.assertEquals(NEW_EMAIL, updatedEmailAddress);
	}

}
