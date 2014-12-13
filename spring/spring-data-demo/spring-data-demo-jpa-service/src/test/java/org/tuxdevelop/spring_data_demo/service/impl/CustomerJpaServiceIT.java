package org.tuxdevelop.spring_data_demo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_data_demo.configuration.PersistenceJPAConfiguration;
import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;
import org.tuxdevelop.spring_data_demo.service.CustomerService;
import org.tuxdevelop.spring_data_demo.util.CustomerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerJpaServiceIT {

    @Autowired
    private CustomerService customerJpaService;

    @Test
    public void addCustomerIT(){
        final Customer customer = CustomerFactory.createCustomer();
        final Customer addedCustomer = customerJpaService.addCustomer(customer);
        Assert.assertNotNull(addedCustomer);
    }

    @Test
    public void getCustomerIT(){
        final Customer customer = CustomerFactory.createCustomer();
        final Customer addedCustomer = customerJpaService.addCustomer(customer);
        Assert.assertNotNull(addedCustomer);
        final Customer gotCustomer = customerJpaService.getCustomer(addedCustomer.getId());
        Assert.assertNotNull(gotCustomer);
    }



}
