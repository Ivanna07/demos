package org.tuxdevelop.spring_boot_cxf_demo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Communication;
import org.tuxdevelop.spring_boot_cxf_demo.domain.CommunicationClassifier;
import org.tuxdevelop.spring_boot_cxf_demo.domain.EmailCommunication;
import org.tuxdevelop.spring_boot_cxf_demo.domain.PhoneCommunication;
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
		final Communication savedEmailCommunication = communicationServiceBean.addCommunication(emailCommunication);
		Assert.assertNotNull(savedEmailCommunication);
		System.err.println(savedEmailCommunication);
	}

	@Test
	public void addCommunicationPhoneCommunicationIT() {
		final PhoneCommunication phoneCommunication = CommunicationFactory
				.createPhoneCommunication(CommunicationClassifier.STANDARD);
		final Communication savedPhoneCommunication = communicationServiceBean.addCommunication(phoneCommunication);
		Assert.assertNotNull(savedPhoneCommunication);
		System.err.println(savedPhoneCommunication);
	}

	@Test
	public void addCommunicationsIT() {
		final PhoneCommunication phoneCommunication = CommunicationFactory
				.createPhoneCommunication(CommunicationClassifier.STANDARD);
		final EmailCommunication emailCommunication = CommunicationFactory
				.createEmailCommunication(CommunicationClassifier.STANDARD);
		final Collection<Communication> communications = new LinkedList<>();
		communications.add(emailCommunication);
		communications.add(phoneCommunication);
		final Collection<Communication> addedCommunications = communicationServiceBean.addCommunications(communications);
		Assert.assertNotNull(addedCommunications);
		Assert.assertFalse(addedCommunications.isEmpty());
		for (final Communication communication : addedCommunications) {
			System.err.println(communication);
		}
	}

	@Test
	public void updateCommunicationIT() {
		final String NEW_NUMBER = "11111";
		final PhoneCommunication phoneCommunication = CommunicationFactory
				.createPhoneCommunication(CommunicationClassifier.STANDARD);
		final Communication savedPhoneCommunication = communicationServiceBean.addCommunication(phoneCommunication);
		((PhoneCommunication) savedPhoneCommunication).setNumber(NEW_NUMBER);
		final Communication updatedCommunication = communicationServiceBean.updateCommunication(
				savedPhoneCommunication.getId(), savedPhoneCommunication);
		Assert.assertNotNull(updatedCommunication);
		final String updatedNumber = ((PhoneCommunication) updatedCommunication).getNumber();
		Assert.assertEquals(NEW_NUMBER, updatedNumber);
	}

}
