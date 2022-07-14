package com.lawencon.asset.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lawencon.asset.model.pojo.EmailPojo;

@Service
public class EmailServiceImpl implements EmailService{

	private JavaMailSender mailSender;
	
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(EmailPojo emailPojo) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply-igk@gmail.com");
		message.setTo(emailPojo.getEmail());
		message.setSubject(emailPojo.getSubject());
		message.setText(emailPojo.getBody());
        mailSender.send(message);
        System.out.println("Email Sent");		
	}

}
