package org.tuxdevelop.spring_boot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tuxdevelop.spring_boot_demo.persistence.domain.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {

}
