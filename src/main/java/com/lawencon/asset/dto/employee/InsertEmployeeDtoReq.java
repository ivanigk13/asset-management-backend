package com.lawencon.asset.dto.employee;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertEmployeeDtoReq {

	@NotNull(message = "user id must be filled")
	private Long userId;
	
	@NotNull(message = "company id must be filled")
	private Long companyId;
	
	@NotEmpty(message = "nip must be filled")
	@Size(min = 1, max = 6, message = "nip length minimum 1 and maximum 6")
	private String nip;
	
	@NotEmpty(message = "employee name must be filled")
	@Size(min = 3, max = 30, message = "employee name length minimum 3 and maximum 30")
	private String employeeName;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
}
