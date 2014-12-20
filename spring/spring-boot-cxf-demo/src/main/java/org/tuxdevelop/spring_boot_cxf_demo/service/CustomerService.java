package org.tuxdevelop.spring_boot_cxf_demo.service;


import org.tuxdevelop.spring_boot_cxf_demo.domain.Customer;

import javax.jws.WebService;

@WebService
public interface CustomerService {

	Customer getCustomer(final Long id);

	Customer addCustomer(final Customer customer);

	Customer updateCustomer(final Long id, final Customer customer);

	void deleteCustomer(final Long id);

}
