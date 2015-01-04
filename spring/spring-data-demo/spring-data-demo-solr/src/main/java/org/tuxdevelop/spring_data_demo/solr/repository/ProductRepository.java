package org.tuxdevelop.spring_data_demo.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.tuxdevelop.spring_data_demo.solr.domain.Product;


public interface ProductRepository extends SolrCrudRepository<Product,String>{
}
