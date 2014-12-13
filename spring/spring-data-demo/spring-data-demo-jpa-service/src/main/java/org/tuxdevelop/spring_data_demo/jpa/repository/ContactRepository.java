package org.tuxdevelop.spring_data_demo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tuxdevelop.spring_data_demo.jpa.domain.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact AS c WHERE c.contactClassifier = 'STANDARD' AND c.customer.id = :customerId")
    public Contact getStandardContactOfCustomer(@Param(value = "customerId")final Long customerId);

}
