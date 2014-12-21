package org.tuxdevelop.spring_boot_cxf_demo.service;


import org.tuxdevelop.spring_boot_cxf_demo.domain.EmailCommunication;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(targetNamespace = "org.tuxdevelop.spring_boot_cxf_demo")
public interface CommunicationService {

    public static final String SERVICE_NAME = "communicationService";

    @WebMethod
    EmailCommunication getCommunication(final Long id);

    @WebMethod
	EmailCommunication addCommunication(final EmailCommunication communication);

    @WebMethod
	Collection<EmailCommunication> addCommunications(final Collection<EmailCommunication> communications);

    @WebMethod
	EmailCommunication updateCommunication(final Long id, final EmailCommunication communication);

    @WebMethod
	Collection<EmailCommunication> updateCommunications(final Collection<EmailCommunication> communications);

    @WebMethod
	void deleteCommunication(final Long id);
}
