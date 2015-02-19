package org.tuxdevelop.demos.spring_data_repositories_cdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tuxdevelop.demos.spring_data_repositories_cdi.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
}
