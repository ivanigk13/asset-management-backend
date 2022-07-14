package com.lawencon.asset.dto.assettype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertAssetTypeDtoReq {

	@NotEmpty(message = "type code must be filled")
	@Size(min = 1, max = 10, message = "type code length minimum 1 and maximum 10")
	private String typeCode;
	
	@NotEmpty(message = "type name must be filled")
	@Size(min = 1, max = 15, message = "type name length minimum 1 and maximum 15")
	private String typeName;
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
