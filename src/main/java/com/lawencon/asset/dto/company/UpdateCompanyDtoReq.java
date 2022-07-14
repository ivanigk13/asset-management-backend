package com.lawencon.asset.dto.company;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateCompanyDtoReq {

	@NotNull(message = "id must be filled")
	private Long id;
	
	@NotNull(message = "version must be filled")
	private Integer version;
	
	@NotEmpty(message = "company name must be filled")
	@Size(min = 1, max = 30, message = "company name length minimum 1 and maximum 30")
	private String companyName;
	
	@NotEmpty(message = "company address must be filled")
	@Size(min = 1, max = 50, message = "company address length minimum 1 and maximum 50")
	private String companyAddress;
	
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
}
