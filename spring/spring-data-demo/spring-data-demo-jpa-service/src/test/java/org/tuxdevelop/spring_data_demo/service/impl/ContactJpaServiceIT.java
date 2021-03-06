package org.tuxdevelop.spring_data_demo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_data_demo.configuration.PersistenceJPAConfiguration;
import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;
import org.tuxdevelop.spring_data_demo.jpa.domain.ContactClassifier;
import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;
import org.tuxdevelop.spring_data_demo.service.ContactService;
import org.tuxdevelop.spring_data_demo.service.CustomerService;
import org.tuxdevelop.spring_data_demo.util.ContactFactory;
import org.tuxdevelop.spring_data_demo.util.CustomerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ContactJpaServiceIT {

    @Autowired
    private ContactService contactJpaService;

    @Autowired
    private CustomerService customerJpaService;

    @Test
    public void addContactIT() {
        final Contact contact = ContactFactory.createContact(ContactClassifier.STANDARD);
        final Contact addedContact = contactJpaService.addContact(contact);
        Assert.assertNotNull(addedContact);
        System.err.println(addedContact);
    }

    @Test
    public void updateContactIT() {
        final String updatedCity = "updatedCity";
        final Contact contact = ContactFactory.createContact(ContactClassifier.STANDARD);
        final Contact addedContact = contactJpaService.addContact(contact);
        Assert.assertNotNull(addedContact);
        addedContact.setCity(updatedCity);
        final Contact updatedContact = contactJpaService.updateContact(addedContact.getId(), addedContact);
        Assert.assertNotNull(updatedContact);
        Assert.assertEquals(updatedCity, updatedContact.getCity());
    }

    @Test
    public void getStandardContactOfCustomerIT() {
        final Customer customer = CustomerFactory.createCustomer();
        final Customer addedCustomer = customerJpaService.addCustomer(customer);
        Assert.assertNotNull(addedCustomer);
        final Contact standardContact = contactJpaService.getStandardContactOfCustomer(addedCustomer.getId());
        Assert.assertNotNull(standardContact);
        System.err.println(standardContact);
        Assert.assertEquals(ContactClassifier.STANDARD,standardContact.getContactClassifier());

    }

}
