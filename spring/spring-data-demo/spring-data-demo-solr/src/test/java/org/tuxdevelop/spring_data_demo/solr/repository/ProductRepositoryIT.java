package org.tuxdevelop.spring_data_demo.solr.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tuxdevelop.spring_data_demo.solr.SpringDataSolrApplication;
import org.tuxdevelop.spring_data_demo.solr.domain.Product;
import org.tuxdevelop.spring_data_demo.solr.util.ProductFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataSolrApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductFactory productFactory;

    @Test
    public void findAllIT(){
        final Iterable<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void findOneIT(){
        final Product product = productFactory.createProduct();
        productRepository.save(product);
        final Product fetchedProduct = productRepository.findOne(product.getId());
        Assert.assertNotNull(fetchedProduct);
        System.out.println(fetchedProduct);
    }
}
