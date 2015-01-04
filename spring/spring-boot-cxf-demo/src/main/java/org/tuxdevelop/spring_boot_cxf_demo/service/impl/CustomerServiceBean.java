package org.tuxdevelop.spring_boot_cxf_demo.service.impl;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Contact;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Customer;
import org.tuxdevelop.spring_boot_cxf_demo.repository.CustomerRepository;
import org.tuxdevelop.spring_boot_cxf_demo.service.ContactService;
import org.tuxdevelop.spring_boot_cxf_demo.service.CustomerService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CustomerServiceBean implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ContactService contactServiceBean;

	@Override
	public Customer getCustomer(final Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer addCustomer(final Customer customer) {
		customer.validateAdd();
		final Collection<Contact> addedContacts = contactServiceBean.addContacts(customer.getContacts());
		customer.setContacts(new LinkedList<>());
		customerRepository.save(customer);
		customer.addContactsToCustomer(addedContacts);
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(final Long id, final Customer customer) {
		customer.validateAdd();
		final Customer fetchedCustomer = customerRepository.findOne(id);
		if (fetchedCustomer != null) {
			final Collection<Contact> updatedContacts = contactServiceBean.updateContacts(customer.getContacts());
			fetchedCustomer.merge(customer);
			fetchedCustomer.setContacts(updatedContacts);
			return customerRepository.save(fetchedCustomer);
		} else {
			throw new RuntimeException("no customer found for id: " + id);
		}
	}

	@Override
	public void deleteCustomer(final Long id) {
		if (customerRepository.exists(id)) {
			customerRepository.delete(id);
		} else {
			throw new RuntimeException("no customer found for id: " + id);
		}
	}

}
