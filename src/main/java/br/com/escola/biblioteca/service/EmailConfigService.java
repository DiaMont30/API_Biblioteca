package br.com.escola.biblioteca.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Configuration
@Service

public class EmailConfigService {
	private final JavaMailSender mailSender = null;
	
@Value("${spring.mail.username}")
private String remetente;

    public void enviar (String destinatario, String assunto, String corpo) {

    try{
	SimpleMailMessage msg = new SimpleMailMessage();
	
	msg.setFrom(remetente);
	msg.setTo(destinatario);
	msg.setSubject(assunto);
	msg.setText(corpo);

    mailSender.send(msg);

    System.out.println("[EMAIL] Enviado com sucesso para: " + destinatario);

    }catch (MailException e) {
    System.out.println("[EMAIL] Falha ao enviar email para " + destinatario + ": " + e.getMessage());  
    }

}

}

