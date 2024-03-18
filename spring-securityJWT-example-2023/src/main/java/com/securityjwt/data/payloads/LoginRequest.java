package com.securityjwt.data.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	@NotBlank
	@Size(max = 20)
	private String username;
	
	@NotBlank
	@Size(max = 120)
	private String password;

	public LoginRequest(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max=120)  String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginRequest() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
