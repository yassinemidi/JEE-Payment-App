package com.myapp.mail;


import java.time.LocalDate;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.myapp.models.abonnement;
import com.myapp.models.facture;
import com.myapp.models.user;


public class JavaMailUtil {
	//
	public static void sendMail(String recepient,user user,facture facture,abonnement abonnement) throws Exception {
		Properties properties=new Properties();
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port","587" );
		
		
		String myemail="johnpayeapp@gmail.com";
		String pass="azer.tyui";
		
		
		Session session=Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(myemail, pass);
			}
			
		});
		//
		Message message=prepareMessage(session, myemail, recepient, user, facture, abonnement);
		Transport.send(message);
		System.out.println("msg send suceesfully");
			
		
	}
	//
	
	private static Message prepareMessage(Session session,String myemail,String recepient,user user,facture facture,abonnement abonnement) {
		
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress("YassineMIDI@ENSA.com"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Paiement de votre facture");
			message.setContent("<div style=\"background-color: aquamarine; padding: 2em;\">\r\n"
					+ "    <h3 style=\"width: max-content;margin: auto;\">Facture de Paiement</h3>\r\n"
					+ "    <div style=\"width: max-content;margin: auto;\">\r\n"
					+ "        <p>\r\n"
					+ "            Le paiement de votre facture a ete effectuer avec succes !\r\n"
					+ "        </p>\r\n"
					+ "        <h5><b>Mr/Mme :</b> "+user.getPrenom()+user.getNom()+"</h5>\r\n"
					+ "        <h5><b>Numero de client :</b> "+user.getId()+"</h5>\r\n"
					+ "        <h5><b>Nom d'abonnement :</b> "+abonnement.getNom()+"</h5>\r\n"
					+ "        <h5><b>Date de debut :</b> "+facture.getDate_debut().toString()+"</h5>\r\n"
					+ "        <h5><b>Montant payee :</b> "+abonnement.getPrice()+"DH"+"</h5>\r\n"
					+ "        <h5><b>payee le :</b> "+LocalDate.now().toString()+"</h5>\r\n"
					+ "    </div>\r\n"
					+ "</div>","text/html");
			return message;
		} catch (Exception e) {
			
		}
		return null;
	}
}
