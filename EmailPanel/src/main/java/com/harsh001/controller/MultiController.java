package com.harsh001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.harsh001.SpringbootEmailPanel;
import com.harsh001.businesslayer.EmailExtractor;
import com.harsh001.businesslayer.SendEmail;
import com.harsh001.businesslayer.designpattern.SendEmailBuilder;

import java.io.*;
import java.util.ArrayList;

@Controller
public class MultiController 
{
	private String filepath = null;
	
	@RequestMapping(value = "/emailpanel")
	public ModelAndView getEmailPanelHomepage()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("EmailPanel");
		return mav;
	}
	
	
	
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public ModelAndView sendEmail(@RequestParam(name = "sender") String username, @RequestParam(name = "pass") String password, @RequestParam(name = "recipient") String recipientType, @RequestParam(name = "receiver") String receiverEmail, @RequestParam(name = "subject") String subject, @RequestParam(name = "typedmessage") String typedMessage, @RequestParam(name = "addrecipient") String filepath, @RequestParam(name = "attachfiles") File files[])
	{
		
		ModelAndView mav = new ModelAndView();
		ArrayList<String> toEmails = new ArrayList<>();
		
		if(filepath != null)
		{
			EmailExtractor emailExtractor = SpringbootEmailPanel.getEmailextractor();
			emailExtractor.setFilepath(filepath);					//Sent the selected recipient file to the Email Extractor //
			toEmails = emailExtractor.extractMail_IDs();		//Extracted all the emails from the recipients list //
		}

		
		if(receiverEmail != null)
		{
			toEmails.add(receiverEmail);		//Added the receiver's email, if the user don't want to provide the recipient list //
		}
		
		toEmails = findEmailsFromList(toEmails);		//Removing null values from the list//
		
		
		ArrayList<String> attachedFiles = new ArrayList<String>();
		for (File f : files) 
		{
            String absolutePath = f.getAbsolutePath(); //gives the absolute path
            attachedFiles.add(absolutePath);
		}
		
		
		
		SendEmailBuilder emailBuilder = SpringbootEmailPanel.getSendbuild();
		emailBuilder.setUsername(username);
		emailBuilder.setPassword(password);
		emailBuilder.setRecipientType(recipientType);
		emailBuilder.setToEmails(toEmails);
		emailBuilder.setSubject(subject);
		emailBuilder.setTypedMessage(typedMessage);
		emailBuilder.setAttachedFiles(attachedFiles);
		
		SendEmail sendEmail = emailBuilder.getProductClassInstance();
		
		mav.setViewName("EmailPanel");
		return mav;
	}
	
	
	
	public ArrayList<String> findEmailsFromList(ArrayList<String> toEmails)		//Method to eliminate null values//
	{
		ArrayList<String> list = new ArrayList<>();
		
		for(String s: toEmails)
		{
			if(!s.contains("@gmail"))
			{
				list.add(s);
			}
		}
		
			toEmails.removeAll(list);
			return toEmails;
	}
}
