package org.tuxdevelop.spring_boot_demo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_demo.configuration.CommonITConfiguration;
import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommonITConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ContactServiceBeanIT extends ServiceITBase {

	@Autowired
	private org.tuxdevelop.spring_boot_demo.service.ContactServiceBean contactServiceBean;

	@Test
	public void addContactIT() {
		final ContactDTO contactDTO = createContactDTO("Donnie", "Darko", "12345", "Frank Town", "Frank Street 28");
		final Long contactId = contactServiceBean.addContact(contactDTO);
		final ContactDTO addedContactDTO = contactServiceBean.getContactById(contactId);
		Assert.assertNotNull(addedContactDTO);
	}

	@Test
	public void updateContactIT() {
		final ContactDTO contactDTO = createContactDTO("Donnie", "Darko", "12345", "Frank Town", "Frank Street 28");
		final Long contactId = contactServiceBean.addContact(contactDTO);
		final ContactDTO addedContactDTO = contactServiceBean.getContactById(contactId);
		Assert.assertNotNull(addedContactDTO);
		addedContactDTO.setFirstName("Donald");
		contactServiceBean.updateContact(addedContactDTO);
		final ContactDTO updatedContactDTO = contactServiceBean.getContactById(contactId);
		Assert.assertNotNull(updatedContactDTO);
		Assert.assertEquals("Donald", updatedContactDTO.getFirstName());
	}

}
