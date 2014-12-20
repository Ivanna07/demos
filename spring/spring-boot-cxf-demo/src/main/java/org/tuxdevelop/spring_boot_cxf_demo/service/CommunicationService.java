package org.tuxdevelop.spring_boot_cxf_demo.service;


import org.tuxdevelop.spring_boot_cxf_demo.domain.Communication;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface CommunicationService {

	Communication getCommunication(final Long id);

	Communication addCommunication(final Communication communication);

	Collection<Communication> addCommunications(final Collection<Communication> communications);

	Communication updateCommunication(final Long id, final Communication communication);

	Collection<Communication> updateCommunications(final Collection<Communication> communications);

	void deleteCommunication(final Long id);
}
