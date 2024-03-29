package com.securityjwt.data.payloads;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
	
	@NotBlank
	@Size(max = 20)
	private String username;
	
	@NotBlank
	@Size(max = 20)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 120)
	private String password;
	
	private Set<String> roles;

	public SignupRequest() {
		super();
	}

	public SignupRequest(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 20) @Email String email,
			@NotBlank @Size(max = 120) String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
