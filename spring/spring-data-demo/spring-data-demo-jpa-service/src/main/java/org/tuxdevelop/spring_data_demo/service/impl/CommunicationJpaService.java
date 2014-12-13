package org.tuxdevelop.spring_data_demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_data_demo.jpa.domain.Communication;
import org.tuxdevelop.spring_data_demo.jpa.domain.CommunicationType;
import org.tuxdevelop.spring_data_demo.jpa.domain.EmailCommunication;
import org.tuxdevelop.spring_data_demo.jpa.domain.PhoneCommunication;
import org.tuxdevelop.spring_data_demo.jpa.repository.CommunicationRepository;
import org.tuxdevelop.spring_data_demo.service.CommunicationService;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Component
public class CommunicationJpaService implements CommunicationService {

	@Autowired
	private CommunicationRepository communicationRepository;

	@Override
	public Communication getCommunication(final Long id) {
		return communicationRepository.findOne(id);
	}

	@Override
	public Communication addCommunication(final Communication communication) {
		return communicationRepository.save(communication);
	}

	@Override
	public Collection<Communication> addCommunications(final Collection<Communication> communications) {
		final Collection<Communication> result = new LinkedList<>();
		if (communications != null && !communications.isEmpty()) {
			result.addAll(communications.stream().map(this::addCommunication).collect(Collectors.toList()));
		}
		return result;
	}

	@Override
	public Communication updateCommunication(final Long id, final Communication communication) {
        final Communication fetchedCommunication = communicationRepository.findOne(id);
		if (fetchedCommunication != null) {
			if (CommunicationType.EMAIL.equals(fetchedCommunication.getCommunicationType())) {
                ((EmailCommunication)communication).validateUpdate();
                ((EmailCommunication) fetchedCommunication).merge((EmailCommunication) communication);
			} else if (CommunicationType.PHONE.equals(fetchedCommunication.getCommunicationType())) {
                ((PhoneCommunication)communication).validateUpdate();
				((PhoneCommunication) fetchedCommunication).merge((PhoneCommunication) communication);
			} else {
				throw new RuntimeException("Invalid Communication Type");
			}
			communicationRepository.save(fetchedCommunication);
		} else {
			throw new RuntimeException("Communication with id: " + id + " not found");
		}
		return fetchedCommunication;
	}

	@Override
	public Collection<Communication> updateCommunications(final Collection<Communication> communications) {
		final Collection<Communication> updatedCommunications = new LinkedList<>();
		if (communications != null && !communications.isEmpty()) {
			updatedCommunications.addAll(communications.stream()
					.map(communication -> updateCommunication(communication.getId(), communication))
					.collect(Collectors.toList()));
		}
		return updatedCommunications;
	}

	@Override
	public void deleteCommunication(final Long id) {
		if (communicationRepository.exists(id)) {
			communicationRepository.delete(id);
		} else {
			throw new RuntimeException("Communication with id: " + id + " does not exist!");
		}
	}

}
