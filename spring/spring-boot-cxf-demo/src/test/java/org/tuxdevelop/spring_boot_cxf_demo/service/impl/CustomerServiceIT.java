package org.tuxdevelop.spring_boot_cxf_demo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Customer;
import org.tuxdevelop.spring_boot_cxf_demo.it_configuration.ITConfiguration;
import org.tuxdevelop.spring_boot_cxf_demo.service.CustomerService;
import org.tuxdevelop.spring_boot_cxf_demo.util.CustomerFactory;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerServiceIT {

    @Autowired
    private CustomerService customerServiceBean;

    @Test
    public void addCustomerIT(){
        final Customer customer = CustomerFactory.createCustomer();
        final Customer addedCustomer = customerServiceBean.addCustomer(customer);
        Assert.assertNotNull(addedCustomer);
    }

    @Test
    public void getCustomerIT(){
        final Customer customer = CustomerFactory.createCustomer();
        final Customer addedCustomer = customerServiceBean.addCustomer(customer);
        Assert.assertNotNull(addedCustomer);
        final Customer gotCustomer = customerServiceBean.getCustomer(addedCustomer.getId());
        Assert.assertNotNull(gotCustomer);
    }

    @Test
    public void updateCustomerIT(){
        final Customer customer = CustomerFactory.createCustomer();
        final Customer addedCustomer = customerServiceBean.addCustomer(customer);
        Assert.assertNotNull(addedCustomer);
        addedCustomer.setChangedBy("changedByMe");
        addedCustomer.setChangedAt(new Date());
        final Customer updatedCustomer = customerServiceBean.updateCustomer(addedCustomer.getId(),addedCustomer);
        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals("changedByMe",updatedCustomer.getChangedBy());

    }


}
