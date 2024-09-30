package com.qp.quantum_share.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.qp.quantum_share.dto.QuantumShareUser;
import com.qp.quantum_share.exception.CommonException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMail {
	@Autowired
	JavaMailSender mailSender;

	public void sendVerificationEmail(QuantumShareUser userDto) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
//		String verificationLink = "https://quantumshare.quantumparadigm.in/verify?token="
//				+ userDto.getVerificationToken();

		String verificationLink = "https://appreview.quantumparadigm.in/verify?token="
				+ userDto.getVerificationToken();
//		String verificationLink = "http://localhost:3000/verify?token=" + userDto.getVerificationToken();
//		String verificationLink = "http://localhost:7532/quantum-socialshare/user/verify?token="
//				+ userDto.getVerificationToken();
		try {
			helper.setFrom("quantumshare12@gmail.com", "QuantumShare");
			helper.setTo(userDto.getEmail());
			helper.setSubject("Welcome to Quantumshare, Please verify your email address");
			String htmlBody = readHtmlTemplate("verification_email_template.html");
			htmlBody = htmlBody.replace("{{USERNAME}}", userDto.getFirstName() + " " + userDto.getLastName());
			htmlBody = htmlBody.replace("{{VERIFICATION_LINK}}", verificationLink);
			helper.setText(htmlBody, true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}

	private String readHtmlTemplate(String templateName) {
		try {
			ClassPathResource resource = new ClassPathResource("templates/" + templateName);
			InputStream inputStream = resource.getInputStream();
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			return new String(bytes, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new CommonException(e.getMessage());
		}
	}
}
