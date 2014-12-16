package org.tuxdevelop.spring_data_demo.rest.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;

//@RepositoryRestResource(path = "customers",collectionResourceRel = "customers")
@RepositoryRestController
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long>{
}
