package org.tuxdevelop.spring_boot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tuxdevelop.spring_boot_demo.persistence.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.emailAddress = :emailAddress")
	public User findByEmailAddress(@Param("emailAddress") final String emailAddress);

	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	public User findByUserName(@Param("userName") final String userName);

}
