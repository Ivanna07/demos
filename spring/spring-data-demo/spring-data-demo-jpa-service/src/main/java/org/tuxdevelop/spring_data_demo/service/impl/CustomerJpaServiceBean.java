package org.tuxdevelop.spring_data_demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;
import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;
import org.tuxdevelop.spring_data_demo.jpa.repository.CustomerRepository;
import org.tuxdevelop.spring_data_demo.service.ContactService;
import org.tuxdevelop.spring_data_demo.service.CustomerService;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class CustomerJpaServiceBean implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContactService contactJpaService;

    @Override
    public Customer getCustomer(final Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer addCustomer(final Customer customer) {
        customer.validateAdd();
        final Collection<Contact> addedContacts = contactJpaService.addContacts(customer.getContacts());
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
            final Collection<Contact> updatedContacts = contactJpaService.updateContacts(customer.getContacts());
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
