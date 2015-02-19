package org.tuxdevelop.spring_boot_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tuxdevelop.spring_boot_demo.persistence.domain.Contact;
import org.tuxdevelop.spring_boot_demo.repository.ContactRepository;
import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;

@Transactional
@Component
public class ContactServiceBean {

	@Autowired
	private ContactRepository contactRepository;

	public Long addContact(final ContactDTO contactDTO) {
		final Contact contact = Contact.buildContact(contactDTO.getFirstName(), contactDTO.getLastName(),
				contactDTO.getZipCode(), contactDTO.getCity(), contactDTO.getStreetLine());
		final Contact savedContact = contactRepository.save(contact);
		return savedContact.getId();
	}

	public void updateContact(final ContactDTO contactDTO) {
		final Contact contact = contactRepository.getOne(contactDTO.getContactId());
		if (contact != null) {
			contact.setCity(contactDTO.getCity());
			contact.setFirstName(contactDTO.getFirstName());
			contact.setLastName(contactDTO.getLastName());
			contact.setStreetLine(contactDTO.getStreetLine());
			contact.setZipCode(contactDTO.getZipCode());
			contactRepository.save(contact);
		} else {
			throw new IllegalArgumentException("contact not found!");
		}
	}

	public ContactDTO getContactById(final Long contactId) {
		final Contact contact = contactRepository.findOne(contactId);
		if (contact == null) {
			throw new IllegalArgumentException("No contact found for contactId: " + contactId);
		}
		return contact.toDTO();
	}
}
