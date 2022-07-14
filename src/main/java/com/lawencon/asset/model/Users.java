package com.lawencon.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users extends BaseModel{

	@ManyToOne
	@JoinColumn(name="role_id")
	private Roles role;
	
	@Column(name="username", length=20)
	private String username;
	
	@Column(name="password", length=100)
	private String password;
	
	@Column(name="email", length=50)
	private String email;
	
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
