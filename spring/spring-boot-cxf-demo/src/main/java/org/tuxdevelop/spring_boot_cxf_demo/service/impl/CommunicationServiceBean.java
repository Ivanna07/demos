package org.tuxdevelop.spring_boot_cxf_demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tuxdevelop.spring_boot_cxf_demo.domain.EmailCommunication;
import org.tuxdevelop.spring_boot_cxf_demo.repository.EmailCommunicationRepository;
import org.tuxdevelop.spring_boot_cxf_demo.service.CommunicationService;

import java.util.Collection;
import java.util.LinkedList;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CommunicationServiceBean implements CommunicationService {

    @Autowired
    private EmailCommunicationRepository emailCommunicationRepository;

    @Override
    public EmailCommunication getCommunication(final Long id) {
        return emailCommunicationRepository.findOne(id);
    }

    @Override
    public EmailCommunication addCommunication(final EmailCommunication emailCommunication) {
        return emailCommunicationRepository.save(emailCommunication);
    }

    @Override
    public Collection<EmailCommunication> addCommunications(final Collection<EmailCommunication> communications) {
        final Collection<EmailCommunication> result = new LinkedList<>();
        if (communications != null && !communications.isEmpty()) {
            for (final EmailCommunication communication : communications) {
                result.add(addCommunication(communication));
            }
        }
        return result;
    }

    @Override
    public EmailCommunication updateCommunication(final Long id, final EmailCommunication communication) {
        final EmailCommunication fetchedCommunication = emailCommunicationRepository.findOne(id);
        if (fetchedCommunication != null) {
            emailCommunicationRepository.save(fetchedCommunication);
        } else {
            throw new RuntimeException("Communication with id: " + id + " not found");
        }
        return fetchedCommunication;
    }

    @Override
    public Collection<EmailCommunication> updateCommunications(final Collection<EmailCommunication> communications) {
        final Collection<EmailCommunication> updatedCommunications = new LinkedList<>();
        if (communications != null && !communications.isEmpty()) {
            for (final EmailCommunication communication : communications) {
                updatedCommunications.add(updateCommunication(communication.getId(), communication));
            }
        }
        return updatedCommunications;
    }

    @Override
    public void deleteCommunication(final Long id) {
        if (emailCommunicationRepository.exists(id)) {
            emailCommunicationRepository.delete(id);
        } else {
            throw new RuntimeException("Communication with id: " + id + " does not exist!");
        }
    }

}
