package org.tuxdevelop.spring_data_demo.jpa.util;

import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;

public class CustomerFactory {

	public static Customer createCustomer() {
		final Customer customer = new Customer();
		return GenericFactory.attachGenericValues(customer);
	}

}
