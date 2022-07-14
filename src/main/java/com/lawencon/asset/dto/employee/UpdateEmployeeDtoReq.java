package com.lawencon.asset.dto.employee;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateEmployeeDtoReq {

	@NotNull(message = "id must be filled")
	private Long id;
	
	@NotNull(message = "version must be filled")
	private Integer version;
	
	@NotEmpty(message = "employee name must be filled")
	@Size(min = 3, max = 30, message = "employee name length minimum 3 and maximum 30")
	private String employeeName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
}
