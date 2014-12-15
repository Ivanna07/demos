package org.tuxdevelop.spring_data_demo.rest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;

@RepositoryRestController
public interface ContactRepository extends PagingAndSortingRepository<Contact,Long>{
}
