package in.ashokit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to, String subject, String body) {
		
		boolean isSent = false;
		try {
			MimeMessage mimeMessage = mailSender .createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			
			helper.setFrom("nirsaboy72@gmail.com");
			helper.setTo("taraprasad3454@gmail.com");
			helper .setSubject("Your Report");
			helper.setText("body", true);
			mailSender.send(mimeMessage);
			isSent = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return isSent;
		}
	}

