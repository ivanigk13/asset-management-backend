package com.lawencon.asset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.asset.security.AuthPrincipalImpl;

public class BaseServiceImpl {

	private AuthPrincipalImpl authPrincipalImpl;

	@Autowired
	public void setOutPrincipalImpl(AuthPrincipalImpl outPrincipalImpl) {
		this.authPrincipalImpl = outPrincipalImpl;
	}
	
	public Long getUserId() {
		if(authPrincipalImpl.getAuthentication().getPrincipal()!=null) {
			return Long.valueOf(authPrincipalImpl.getAuthentication().getPrincipal().toString());
		}		
		throw new RuntimeException("invalid login");
	}
}
