package com.securityjwt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securityjwt.data.entities.Role;
import com.securityjwt.data.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByName(ERole role);

}
