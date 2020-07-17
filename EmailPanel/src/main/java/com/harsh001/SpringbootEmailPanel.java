package com.harsh001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.harsh001.businesslayer.EmailExtractor;
import com.harsh001.businesslayer.SendEmail;
import com.harsh001.businesslayer.designpattern.SendEmailBuilder;

@SpringBootApplication
@ComponentScan(basePackages = "com.harsh001.businesslayer, com.harsh001.businesslayer.designpattern, com.harsh001.controller")
public class SpringbootEmailPanel {
	private static SendEmailBuilder sendbuild;
	private static EmailExtractor emailextractor;
	
	
	

	public static EmailExtractor getEmailextractor() {
		return emailextractor;
	}

	public static void setEmailextractor(EmailExtractor emailextractor) {
		SpringbootEmailPanel.emailextractor = emailextractor;
	}

	public static SendEmailBuilder getSendbuild() {
		return sendbuild;
	}

	public static void setSendbuild(SendEmailBuilder sendbuild) {
		SpringbootEmailPanel.sendbuild = sendbuild;
	}


	public static void main(String[] args) 
	{
		ConfigurableApplicationContext con = SpringApplication.run(SpringbootEmailPanel.class, args);
		
		setSendbuild(con.getBean(SendEmailBuilder.class));		//Spring Container i.e. Dependency Injection//
		
		setEmailextractor(con.getBean(EmailExtractor.class));	//Spring Container i.e. Dependency Injection//
	}

}
