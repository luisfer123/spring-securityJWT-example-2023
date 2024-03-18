package com.securityjwt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.securityjwt.data.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
	
	@Query(value = "select u from User u join fetch u.roles where u.username = :username",
			countQuery = "select count(u) from User u join fetch u.roles where u.username = :username")
	Optional<User> findByUsernameWithRoles(@Param("username") String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

}
