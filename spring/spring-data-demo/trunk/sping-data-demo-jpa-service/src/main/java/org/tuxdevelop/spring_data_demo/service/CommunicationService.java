package org.tuxdevelop.spring_data_demo.service;

import java.util.Collection;

import org.tuxdevelop.spring_data_demo.jpa.domain.Communication;

public interface CommunicationService {

	Communication getCommunication(final Long id);

	Communication addCommunication(final Communication communication);

	Collection<Communication> addCommunications(final Collection<Communication> communications);

	Communication updateCommunication(final Long id, final Communication communication);

	Collection<Communication> updateCommunications(final Collection<Communication> communications);

	void deleteCommunication(final Long id);
}
