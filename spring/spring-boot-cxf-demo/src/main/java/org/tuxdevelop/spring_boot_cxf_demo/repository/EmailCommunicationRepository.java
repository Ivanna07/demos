package org.tuxdevelop.spring_boot_cxf_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tuxdevelop.spring_boot_cxf_demo.domain.EmailCommunication;

public interface EmailCommunicationRepository extends JpaRepository<EmailCommunication, Long> {

}
