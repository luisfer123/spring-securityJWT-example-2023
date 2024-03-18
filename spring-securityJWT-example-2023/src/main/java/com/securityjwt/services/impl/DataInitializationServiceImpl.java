package com.securityjwt.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securityjwt.data.entities.Role;
import com.securityjwt.data.entities.User;
import com.securityjwt.data.enums.ERole;
import com.securityjwt.repositories.RoleRepository;
import com.securityjwt.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class DataInitializationServiceImpl {
	
	@Autowired PasswordEncoder encoder;
	@Autowired UserRepository userRepo;
	@Autowired RoleRepository roleRepo;
	
	@Transactional
	@EventListener(classes = ApplicationReadyEvent.class)
	public void onInit() {
		addAuthorities();
		addUsers();
	}
	
	private void addAuthorities() {
		if(roleRepo.count() == 0) {
			Role adminRole = new Role(ERole.ROLE_ADMIN);
			Role moderatorRole = new Role(ERole.ROLE_MODERATOR);
			Role userRole = new Role(ERole.ROLE_USER);
			
			roleRepo.saveAll(List.of(adminRole, moderatorRole, userRole));
		}
	}
	
	private void addUsers() {
		if(userRepo.count() == 0) {
			Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN).get();
			Role moderatorRole = roleRepo.findByName(ERole.ROLE_MODERATOR).get();
			Role userRole = roleRepo.findByName(ERole.ROLE_USER).get();
			
			User admin = User.Builder.newInstance()
					.setUsername("admin")
					.setEmail("admin@mail.com")
					.setPassword(encoder.encode("password"))
					.setRoles(Set.of(adminRole, moderatorRole, userRole))
					.build();
			
			User moderator = User.Builder.newInstance()
					.setUsername("moderator")
					.setEmail("moderator@mail.com")
					.setPassword(encoder.encode("password"))
					.setRoles(Set.of(moderatorRole, userRole))
					.build();
			
			User user = User.Builder.newInstance()
					.setUsername("user")
					.setEmail("user@mail.com")
					.setPassword(encoder.encode("password"))
					.setRoles(Set.of(userRole))
					.build();
			
			userRepo.saveAll(List.of(admin, moderator, user));
		}
	}

}
