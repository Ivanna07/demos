package org.tuxdevelop.spring_data_demo.service;

import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;

public interface CustomerService {

	Customer getCustomer(final Long id);

	Customer addCustomer(final Customer customer);

	Customer updateCustomer(final Long id, final Customer customer);

	void deleteCustomer(final Long id);

}
