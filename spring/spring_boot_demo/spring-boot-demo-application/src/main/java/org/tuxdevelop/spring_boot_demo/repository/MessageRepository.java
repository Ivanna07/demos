package org.tuxdevelop.spring_boot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tuxdevelop.spring_boot_demo.persistence.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
