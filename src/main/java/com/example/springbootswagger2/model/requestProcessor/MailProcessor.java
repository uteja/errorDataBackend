package com.example.springbootswagger2.model.requestProcessor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class MailProcessor {
	
	public static String fromMail = "udayk998@yahoo.com";
	public static String fromMailPass = "Uteja@998";
	
	public static String toMail = "uteja70@gmail.com";
	
	public String sendMail(String mailId) {
		//from,password,to,subject,message  
	     return send(fromMail,"fromMailPass",fromMail,"hello","How r u?");  
	     //change from, password and to  
	}
	
	public static String send(String from,String password,String to,String sub,String msg){  
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.port", "587");
		props.put("mail.host", "smtp.mail.yahoo.com");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return "202";
	}
}
