package com.harsh001.businesslayer.designpattern;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.harsh001.SpringbootEmailPanel;
import com.harsh001.businesslayer.SendEmail;

//Builder Design pattern is getting used here, so that code complexity can be reduced//
@Component
public class SendEmailBuilder 
{
	private String username, password, subject, typedMessage, recipientType;
	private ArrayList<String> toEmails = new ArrayList<String>();
    private ArrayList<String> attachedFiles = new ArrayList<String>();
	
    
    public SendEmail getProductClassInstance()		//Dependency Injection is been used here //
    {
    	return new SendEmail(username, password, recipientType, attachedFiles, toEmails, subject, typedMessage);
    }


	public SendEmailBuilder setUsername(String username) {
		this.username = username;
		return this;
	}


	public SendEmailBuilder setPassword(String password) {
		this.password = password;
		return this;
	}


	public SendEmailBuilder setSubject(String subject) {
		this.subject = subject;
		return this;
	}


	public SendEmailBuilder setTypedMessage(String typedMessage) {
		this.typedMessage = typedMessage;
		return this;
	}


	public SendEmailBuilder setRecipientType(String recipientType) {
		this.recipientType = recipientType;
		return this;
	}


	public SendEmailBuilder setToEmails(ArrayList<String> toEmails) {
		this.toEmails = toEmails;
		return this;
	}


	public SendEmailBuilder setAttachedFiles(ArrayList<String> attachedFiles) {
		this.attachedFiles = attachedFiles;
		return this;
	}
    
    
    
    
}
