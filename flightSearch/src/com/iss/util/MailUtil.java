package com.iss.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class MailUtil {

	public static String myEmailAccount="157449556@qq.com";
	public static String myEmailPassword="zxvwgabsqvypcaji";
	public static String myEmailSMTPHost="smtp.qq.com";
	public static String smtpPort="465";
	
	public static void sendMail(String receiveMailAccount,String str1) {
		try {
			Properties props=new Properties();
			
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.smtp.host", myEmailSMTPHost);
			props.setProperty("mail.smtp.auth", "true");
			
			props.setProperty("mail.smtp.port", smtpPort);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.socketFactory.port", smtpPort);
			
			Session session=Session.getDefaultInstance(props);
			session.setDebug(true);
			
			MimeMessage message=new MimeMessage(session);
			//发件人
			message.setFrom(new InternetAddress(myEmailAccount,"航空公司","UTF-8"));
			
			//收件人
			message.setRecipient(MimeMessage.RecipientType.TO, 
					new InternetAddress(receiveMailAccount,"亲爱的用户","UTF-8"));
			
			//邮件的主题
			message.setSubject("找回密码","UTF-8");
			
			//邮件的正文部分
			message.setContent(str1 + " ","text/html; charset=UTF-8");
			
			//设定发件时间
			message.setSentDate(new Date());
			
			//保存邮件设置
			message.saveChanges();
			
			
			Transport transport=session.getTransport();
			transport.connect(myEmailAccount,myEmailPassword);
			
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
