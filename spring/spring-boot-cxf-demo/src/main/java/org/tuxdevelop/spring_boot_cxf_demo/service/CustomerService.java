package org.tuxdevelop.spring_boot_cxf_demo.service;


import org.tuxdevelop.spring_boot_cxf_demo.domain.Customer;

import javax.jws.WebService;

@WebService(targetNamespace = "org.tuxdevelop.spring_boot_cxf_demo")
public interface CustomerService {

    static String SERVICE_NAME = "customerService";

	Customer getCustomer(final Long id);

	Customer addCustomer(final Customer customer);

	Customer updateCustomer(final Long id, final Customer customer);

	void deleteCustomer(final Long id);

}
