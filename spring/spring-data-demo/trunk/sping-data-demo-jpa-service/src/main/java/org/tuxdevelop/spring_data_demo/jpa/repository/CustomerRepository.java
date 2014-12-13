package org.tuxdevelop.spring_data_demo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tuxdevelop.spring_data_demo.jpa.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
