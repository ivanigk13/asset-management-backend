package com.lawencon.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="file")
public class File extends BaseModel{
	
	@Column(name="extension_name")
	private String extensionName;
	
	@Column(name="content")
	private byte[] content;
	
	public String getExtensionName() {
		return extensionName;
	}
	
	public void setExtensionName(String extensionName) {
		this.extensionName = extensionName;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
}
