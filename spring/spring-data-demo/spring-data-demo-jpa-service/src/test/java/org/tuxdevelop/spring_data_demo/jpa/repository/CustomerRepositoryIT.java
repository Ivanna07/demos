package org.tuxdevelop.spring_data_demo.jpa.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_data_demo.configuration.PersistenceJPAConfiguration;
import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;
import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;
import org.tuxdevelop.spring_data_demo.util.CustomerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfiguration.class)
public class CustomerRepositoryIT {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void getCustomerIT() {
		final Customer customer = customerRepository.getOne(1L);
		Assert.assertNull(customer);
	}

	@Test
	public void addCustomerIT() {
		final Customer customer = CustomerFactory.createCustomer();
		customerRepository.save(customer);
		final Customer gotCustomer = customerRepository.findOne(customer.getId());
		Assert.assertNotNull(gotCustomer);
		System.err.println(gotCustomer);
	}

}
