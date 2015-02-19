package org.tuxdevelop.spring_boot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.tuxdevelop.spring_boot_demo.persistence.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailAddress(@Param("emailAddress") final String emailAddress);

	public User findByUserName(@Param("userName") final String userName);

}
