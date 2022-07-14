package com.lawencon.asset.dto.assetstatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateAssetStatusDtoReq {

	@NotNull(message = "id must be filled")
	private Long id;
	
	@NotNull(message = "version must be filled")
	private Integer version;
	
	@NotEmpty(message = "status name must be filled")
	@Size(min = 1, max = 15, message = "status name length minimum 1 and maximum 15")
	private String statusName;
	
	@NotEmpty(message = "alt name must be filled")
	@Size(min = 1, max = 15, message = "status name length minimum 1 and maximum 15")
	private String altName;
	
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getAltName() {
		return altName;
	}
	public void setAltName(String altName) {
		this.altName = altName;
	}
	
}
