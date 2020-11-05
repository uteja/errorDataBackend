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

import com.example.springbootswagger2.model.ErrorData;
import com.sun.mail.smtp.SMTPTransport;

public class MailProcessor {
	
	public static String fromMail = "udayk998@yahoo.com";
	public static String fromMailPass = "Uteja@998";
	public static String toMail = "uteja70@gmail.com";
	
	public String sendMail(String mailId, ErrorData errorData) {
		String subject = errorData.getAccountName() + " : " + errorData.getErrorType() + " error occured";
		String body = "<html>" + "<h2 style=\"background-color:powderblue;\">Welcome To Stackers Report</h2>"
				+ "</body>"
				+ "<head><style>table, th, td {  border: 1px solid black; } </style></head><body>"
				+ "<h2>Detail Report</h2>" + "<table style=\"width:100%\">" + "<tr>" + "<th>Account Name</th>"
				+ "<th>Account Number</th>" + "<th>Time Stamp</th>" + "<th>Status</th>" + "</tr>" + "<tr>" + "<td>"
				+ errorData.getAccountName() + "</td>" + "<td>" + errorData.getAccountNumber() + "</td>" + "<td>"
				+ errorData.getTimeStamp() + "</td>" + "<td>" + errorData.getStatus() + "</td>" + "</tr>" 
				+ "</table>"+ "</br> </br>" + "<table style=\"width:100%\">" + "<tr>" + "<th>System</th>"
				+ "<th>Error Type</th>" + "<th>Error Details</th>" + "<th>Error Code</th>"
				+ "<th>Error Criticality</th>" + "</tr>" + "<td>" + errorData.getResponsibleSystem() + "</td>" + "<td>"
				+ errorData.getErrorType() + "</td>" + "<td>" + errorData.getErrorDetails() + "</td>" + "<td>"
				+ errorData.getErrorCode() + "</td>" + "<td>" + errorData.getErrorCriticality() + "</td>" + "</tr>"
				+ "</table></body></html><p>Reagards,</p></n><p>Stackers Team</p>";
		//from,password,to,subject,message  
		return send(fromMail,"fromMailPass",mailId,subject,body);  
	}
	
	public static String send(String from,String password,String to,String sub,String body){  
		String host = "smtp.mail.yahoo.com";
		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("udayk998", "zitipihhvoinkemf");
			}
		});

		session.setDebug(true);
		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setContent(body,"text/html");

			System.out.println("sending...");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			if(!mex.getMessage().isEmpty()) {
				return "400";
			}
		} 
		return "202";
	}
}
