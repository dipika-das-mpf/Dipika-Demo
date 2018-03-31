package com.mplatform.framework.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.sun.mail.smtp.SMTPTransport;

public class MailHTMLReports 
{
	//============================SENDS THE EMAIL===================================
	public MailHTMLReports(String strToMailingList) throws Exception 
	{
		CommonFunctions objCMNFunctions = new CommonFunctions();
		new HTMLFunctions(null);
		//==================READ MODULEWISE REPORT HTML AND STORE ALL THE LINES IN STRING BUFFER=============================
		String strModuleFilePath = HTMLFunctions.strModuleResultsFilePath;
		new HTMLFunctions(null);
		String strStepResultsFilePath = HTMLFunctions.strResultsFilePath;
		new HTMLFunctions(null);
		String strResultsFolderPath = HTMLFunctions.strCurrentExecutionFolderPath;
		new ZipUtils();		
		
		//===================ZIP THE EXECUTION REPORT FOLDER========================================
		
		BufferedReader objBFRReader = new BufferedReader(new FileReader(strModuleFilePath));
		
		String strCurrentLine = "";
		StringBuffer objStringBuffer = new StringBuffer();
		while ((strCurrentLine = objBFRReader.readLine()) != null) 
		{
			objStringBuffer.append(strCurrentLine);
		}
		objBFRReader.close();
		
		
		//===================SEND HTML MAIL==========================================================	
		Properties objMailProperties = System.getProperties();
		objMailProperties.put("mail.smtps.host", "smtp.gmail.com");
		objMailProperties.put("mail.smtps.auth", "true");
		
		Session objSession = Session.getInstance(objMailProperties, null);
		Message objMessage = new MimeMessage(objSession);
		
		objMessage.setFrom(new InternetAddress("qa.medialets@gmail.com"));
		
		objMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(strToMailingList, false));
		objMessage.setSubject("["+ GlobalPaths.strGLEnvironment + "] AUTOMATION EXECUTION REPORT " + objCMNFunctions.GetDateTimeString());
//		objMessage.setDataHandler(new DataHandler(new ByteArrayDataSource(objStringBuffer.toString(), "text/html")));
		
		MimeBodyPart objMIMEPart1 = new MimeBodyPart();
		objMIMEPart1.setDataHandler(new DataHandler(new ByteArrayDataSource(objStringBuffer.toString(), "text/html")));
		MimeBodyPart objMIMEPart2 = new MimeBodyPart();
		objMIMEPart2.attachFile(strResultsFolderPath + ".zip");
//		MimeBodyPart objMIMEPart3 = new MimeBodyPart();
//		objMIMEPart3.attachFile(strStepResultsFilePath);
		MimeMultipart objMultiPart = new MimeMultipart();
		objMultiPart.addBodyPart(objMIMEPart1);
		objMultiPart.addBodyPart(objMIMEPart2);
//		objMultiPart.addBodyPart(objMIMEPart3);
		objMessage.setContent(objMultiPart);	
		objMessage.setHeader("X-Mailer", "smtpsend");
		objMessage.setSentDate(new Date());
		SMTPTransport objTransport = (SMTPTransport)objSession.getTransport("smtps");
		objTransport.connect("smtp.gmail.com", "qa.medialets@gmail.com", "medialets_qa");
		objTransport.sendMessage(objMessage, objMessage.getAllRecipients());
		objTransport.close();
		
		
	
	}

	
	public static void main(String[] args) {
	}

}
