package org.tuxdevelop.demos.spring_data_repositories_cdi.service;

import org.tuxdevelop.demos.spring_data_repositories_cdi.domain.Customer;

public interface CustomerService {

    Customer addCustomer(final Customer customer);
    Customer getCustomer(final Long id);

}
