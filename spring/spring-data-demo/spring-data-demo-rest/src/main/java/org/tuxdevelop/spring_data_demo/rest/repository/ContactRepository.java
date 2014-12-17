package org.tuxdevelop.spring_data_demo.rest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;
import org.tuxdevelop.spring_data_demo.jpa.domain.ContactClassifier;

//@RepositoryRestResource(path = "contacts",collectionResourceRel = "contacts")
@RepositoryRestController
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    Contact findByCustomerIdAndContactClassifier(@Param("customerId") Long customerId, @Param("contactClassifier")
    ContactClassifier contactClassifier);

}
