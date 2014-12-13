package org.tuxdevelop.spring_data_demo.jpa.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_data_demo.configuration.PersistenceJPAConfiguration;
import org.tuxdevelop.spring_data_demo.jpa.domain.CommunicationClassifier;
import org.tuxdevelop.spring_data_demo.jpa.domain.EmailCommunication;
import org.tuxdevelop.spring_data_demo.util.CommunicationFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfiguration.class)
public class CommunicationRepositoryIT {

	@Autowired
	private CommunicationRepository communicationRepository;

	@Test
	public void addEmailCommunication() {
		final EmailCommunication emailCommunication = CommunicationFactory
				.createEmailCommunication(CommunicationClassifier.STANDARD);
		communicationRepository.save(emailCommunication);
		final EmailCommunication gotEmailCommunication = (EmailCommunication) communicationRepository
				.findOne(emailCommunication.getId());
		Assert.assertNotNull(gotEmailCommunication);
		System.err.println(gotEmailCommunication);
	}
}
