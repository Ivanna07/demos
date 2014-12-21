package org.tuxdevelop.spring_boot_cxf_demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Contact;
import org.tuxdevelop.spring_boot_cxf_demo.domain.EmailCommunication;
import org.tuxdevelop.spring_boot_cxf_demo.repository.ContactRepository;
import org.tuxdevelop.spring_boot_cxf_demo.service.CommunicationService;
import org.tuxdevelop.spring_boot_cxf_demo.service.ContactService;

import java.util.Collection;
import java.util.LinkedList;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ContactServiceBean implements ContactService {

    @Autowired
    private CommunicationService communicationServiceBean;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact getContact(final Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact addContact(final Contact contact) {
        contact.validateAdd();
        final Collection<EmailCommunication> addedEmailCommunications = communicationServiceBean.addCommunications(contact
                .getEmailCommunications());
        contact.setEmailCommunications(new LinkedList<EmailCommunication>());
        contactRepository.save(contact);
        contact.addCommunicationsToContact(addedEmailCommunications);
        return contactRepository.save(contact);
    }

    @Override
    public Collection<Contact> addContacts(final Collection<Contact> contacts) {
        final Collection<Contact> addedContacts = new LinkedList<>();
        if (contacts != null && !contacts.isEmpty()) {
            for(final Contact contact : contacts){
                addedContacts.add(addContact(contact));
            }
        }
        return addedContacts;
    }

    @Override
    public Contact updateContact(final Long id, final Contact contact) {
        contact.validateUpdate();
        final Contact fetchedContact = contactRepository.findOne(id);
        if (fetchedContact != null) {
            fetchedContact.merge(contact);
            final Collection<EmailCommunication> updatedEmailCommunications = communicationServiceBean.updateCommunications
                    (contact.getEmailCommunications());
            fetchedContact.setEmailCommunications(updatedEmailCommunications);
            return contactRepository.save(fetchedContact);
        } else {
            throw new RuntimeException("Could not update contact. No contact with id: " + id + " exists");
        }
    }

    @Override
    public Collection<Contact> updateContacts(final Collection<Contact> contacts) {
        final Collection<Contact> updatedContacts = new LinkedList<>();
        if (contacts != null && !contacts.isEmpty()) {
            for(final Contact contact : contacts){
                updatedContacts.add(updateContact(contact.getId(),contact));
            }
        }
        return updatedContacts;
    }

    @Override
    public void deleteContact(final Long id) {
        if (contactRepository.exists(id)) {
            contactRepository.delete(id);
        } else {
            throw new RuntimeException("Could not delete Contact. No contact found for id : " + id);
        }
    }

    @Override
    public Contact getStandardContactOfCustomer(final Long customerId) {
        return contactRepository.getStandardContactOfCustomer(customerId);
    }

}
