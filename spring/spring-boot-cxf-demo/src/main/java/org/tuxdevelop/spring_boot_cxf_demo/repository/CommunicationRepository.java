package org.tuxdevelop.spring_boot_cxf_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tuxdevelop.spring_boot_cxf_demo.domain.Communication;

public interface CommunicationRepository extends JpaRepository<Communication, Long> {

}
