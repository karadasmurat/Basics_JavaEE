package ejb;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class MailService {

	private Session session;
	private static final Logger LOGGER = Logger.getLogger(MailService.class.getName());

	public MailService() {
	}

	public Future sendMessage(String recipient_TO) {
		LOGGER.log(Level.FINE, "MailService.sendMessage()");
		String status;
		try {
			Message message = new MimeMessage(session);
			message.setFrom();
			message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(recipient_TO, false));
			message.setSubject("Test message from async example");
			message.setHeader("X-Mailer", "JavaMail");
			DateFormat dateFormatter = DateFormat.getDateTimeInstance(1, 3);
			Date timeStamp = new Date();
			String messageBody = (new StringBuilder(
					"This is a test message from the async example of the Java EE Tutorial. It was sent on "))
					.append(dateFormatter.format(timeStamp)).append(".").toString();
			message.setText(messageBody);
			message.setSentDate(timeStamp);
			Transport.send(message);
			status = "Sent";
			LOGGER.log(Level.INFO, "Mail sent to {0}", recipient_TO);
		} catch (MessagingException ex) {
			LOGGER.severe("Error in sending message.");
			status = (new StringBuilder("Encountered an error: ")).append(ex.getMessage()).toString();
			LOGGER.severe(ex.getMessage());
		}
		return new AsyncResult(status);
	}

}
