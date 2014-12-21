package org.tuxdevelop.spring_boot_cxf_demo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Contact;
import org.tuxdevelop.spring_boot_cxf_demo.domain.ContactClassifier;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Customer;
import org.tuxdevelop.spring_boot_cxf_demo.it_configuration.ITConfiguration;
import org.tuxdevelop.spring_boot_cxf_demo.service.ContactService;
import org.tuxdevelop.spring_boot_cxf_demo.service.CustomerService;
import org.tuxdevelop.spring_boot_cxf_demo.util.ContactFactory;
import org.tuxdevelop.spring_boot_cxf_demo.util.CustomerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ContactServiceIT {

    @Autowired
    private ContactService contactServiceBean;

    @Autowired
    private CustomerService customerServiceBean;

    @Test
    public void addContactIT() {
        final Contact contact = ContactFactory.createContact(ContactClassifier.STANDARD);
        final Contact addedContact = contactServiceBean.addContact(contact);
        Assert.assertNotNull(addedContact);
        System.err.println(addedContact);
    }

    @Test
    public void updateContactIT() {
        final String updatedCity = "updatedCity";
        final Contact contact = ContactFactory.createContact(ContactClassifier.STANDARD);
        final Contact addedContact = contactServiceBean.addContact(contact);
        Assert.assertNotNull(addedContact);
        addedContact.setCity(updatedCity);
        final Contact updatedContact = contactServiceBean.updateContact(addedContact.getId(), addedContact);
        Assert.assertNotNull(updatedContact);
        Assert.assertEquals(updatedCity, updatedContact.getCity());
    }

    @Test
    public void getStandardContactOfCustomerIT() {
        final Customer customer = CustomerFactory.createCustomer();
        final Customer addedCustomer = customerServiceBean.addCustomer(customer);
        Assert.assertNotNull(addedCustomer);
        final Contact standardContact = contactServiceBean.getStandardContactOfCustomer(addedCustomer.getId());
        Assert.assertNotNull(standardContact);
        System.err.println(standardContact);
        Assert.assertEquals(ContactClassifier.STANDARD,standardContact.getContactClassifier());

    }

}
