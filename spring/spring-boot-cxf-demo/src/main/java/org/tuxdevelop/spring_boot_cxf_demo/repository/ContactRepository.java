package org.tuxdevelop.spring_boot_cxf_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query(value = "SELECT c FROM Contact AS c WHERE c.contactClassifier = 'STANDARD' AND c.customer.id = :customerId")
	public Contact getStandardContactOfCustomer(@Param(value = "customerId") final Long customerId);

}
