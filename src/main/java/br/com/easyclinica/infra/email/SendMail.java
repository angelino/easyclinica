package br.com.easyclinica.infra.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.ioc.Component;

@Component 
public class SendMail {
	
	private static final String PWD = "c3bolacombanan@_#gmail";
	private static final String USER = "easyclinica@gmail.com";

	private static Logger log = Logger.getLogger(SendMail.class);
	
	private String mailSMTPServer;
	private String mailSMTPServerPort;
	
	public SendMail() { 
		mailSMTPServer = "smtp.gmail.com";
		mailSMTPServerPort = "465";
	}
	
	public void sendMail(String from, String[] to, String subject, String message) {
		
		Properties props = new Properties();

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.host", mailSMTPServer);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.user", from);
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", mailSMTPServerPort);
		props.put("mail.smtp.socketFactory.port", mailSMTPServerPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		SimpleAuth auth = null;
		auth = new SimpleAuth (USER,PWD);
		
		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(false);

		Message msg = new MimeMessage(session);

		try {
			InternetAddress[] addresses = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				addresses[i] = new InternetAddress(to[i]);
			}
			
			msg.setRecipients(Message.RecipientType.TO, addresses);
			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);
			msg.setContent(message,"text/plain");

		} catch (Exception e) {
			log.error("when trying to send email", e);
		}
		
		Transport tr;
		try {
			tr = session.getTransport("smtp");

			tr.connect(mailSMTPServer, USER, PWD);
			msg.saveChanges(); // don't forget this
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			log.error("wen trying to send email", e);
		}

	}
}

class SimpleAuth extends Authenticator {
	public String username = null;
	public String password = null;


	public SimpleAuth(String user, String pwd) {
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication (username,password);
	}
} 

