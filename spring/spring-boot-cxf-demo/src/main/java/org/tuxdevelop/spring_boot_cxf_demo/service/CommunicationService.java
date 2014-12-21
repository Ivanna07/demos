package org.tuxdevelop.spring_boot_cxf_demo.service;


import org.tuxdevelop.spring_boot_cxf_demo.domain.Communication;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(targetNamespace = "org.tuxdevelop.spring_boot_cxf_demo")
public interface CommunicationService {

    public static final String SERVICE_NAME = "communicationService";

    @WebMethod
	Communication getCommunication(final Long id);

    @WebMethod
	Communication addCommunication(final Communication communication);

    @WebMethod
	Collection<Communication> addCommunications(final Collection<Communication> communications);

    @WebMethod
	Communication updateCommunication(final Long id, final Communication communication);

    @WebMethod
	Collection<Communication> updateCommunications(final Collection<Communication> communications);

    @WebMethod
	void deleteCommunication(final Long id);
}
