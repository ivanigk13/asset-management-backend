package com.lawencon.asset.dto.file;

public class InsertFileDtoReq {

	private String extensionName;
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
