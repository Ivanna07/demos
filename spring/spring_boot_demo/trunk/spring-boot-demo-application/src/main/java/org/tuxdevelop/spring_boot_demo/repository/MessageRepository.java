package org.tuxdevelop.spring_boot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tuxdevelop.spring_boot_demo.persistence.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
