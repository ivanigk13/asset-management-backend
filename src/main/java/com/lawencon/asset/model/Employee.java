package com.lawencon.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee extends BaseModel{

	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@Column(name="nip", length=6)
	private String nip;
	
	@Column(name="employee_name", length=30)
	private String employeeName;
	
	public void setUser(Users user) {this.user = user;}
	public Users getUser() {return user;}
	
	public void setCompany(Company company) {this.company = company;}
	public Company getCompany() {return company;}
	
	public void setNip(String nip) {this.nip = nip;}
	public String getNip() {return nip;}
	
	public void setEmployeeName(String employeeName) {this.employeeName = employeeName;}
	public String getEmployeeName() {return employeeName;}
}
