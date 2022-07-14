package com.lawencon.asset.email;

import com.lawencon.asset.model.pojo.EmailPojo;

public interface EmailService {	
	public void sendEmail(EmailPojo emailPojo) throws Exception;	
}
