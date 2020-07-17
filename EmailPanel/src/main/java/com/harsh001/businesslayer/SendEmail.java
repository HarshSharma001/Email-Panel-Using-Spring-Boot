package com.harsh001.businesslayer;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.stereotype.Component;
@Component
public class SendEmail {

    Properties prop = System.getProperties();

    String username = null;
    String password = null;

    private String subject = null, typedMessage = null, recipientType=null;
    private int i = 0;

    private List<String> toEmails = new ArrayList<String>();
    private List<String> attachedFiles = new ArrayList<String>();
    
    Message.RecipientType mrt1 = RecipientType.TO;
    Message.RecipientType mrt2 = RecipientType.CC;
    Message.RecipientType mrt3 = RecipientType.BCC;
    
    Message.RecipientType messageRecipientType=null ;
    
    public SendEmail() {};

    public SendEmail(String username, String password, String recipientType, ArrayList<String> attachedFiles, ArrayList<String> toEmails, String subject, String typedMessage) {

        this.username = username;
        this.password = password;
        this.recipientType = recipientType;
        this.attachedFiles = attachedFiles;
        this.toEmails = toEmails;
        this.subject = subject;
        this.typedMessage = typedMessage;
        
        if(recipientType == "To")
        {
        	messageRecipientType = javax.mail.Message.RecipientType.TO;
        }
        
        else if(recipientType == "CC")
        {
        	messageRecipientType = javax.mail.Message.RecipientType.CC;
        }
        
        else if(recipientType == "BCC")
        {
        	messageRecipientType = javax.mail.Message.RecipientType.BCC;
        }
        
        else 
        {
        	messageRecipientType = javax.mail.Message.RecipientType.TO;
        }

        
        
        
        try {

            prop.setProperty("mail.smtp.host", "smtp.gmail.com");
            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.smtp.socketFactory.fallback", "false");
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.debug", "true");
            prop.put("mail.store.protocol", "pop3");
            prop.put("mail.transport.protocol", "smtp");

            Session session = Session.getDefaultInstance(prop,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    }); //to get hold on Session class instance //

           
            
            MimeMessage message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            
            message.setFrom(new InternetAddress(username));
            
            	for (String email : toEmails) 
            	{
                message.addRecipient(messageRecipientType, new InternetAddress(email));
                }

                message.setSubject(subject);
                BodyPart messageBodyPart1 = new MimeBodyPart();
                messageBodyPart1.setText(typedMessage);
                
                multipart.addBodyPart(messageBodyPart1, i);
                i = i + 1;

                for (String filename : attachedFiles) 
                {
                    MimeBodyPart messageBodyPart2 = new MimeBodyPart();
                    messageBodyPart2.attachFile(filename);

                    multipart.addBodyPart(messageBodyPart2, i);
                    i = i + 1;
                }

                message.setContent(multipart);
                Transport.send(message);

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

