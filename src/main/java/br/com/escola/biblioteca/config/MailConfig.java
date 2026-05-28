package br.com.escola.biblioteca.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

@Configuration
public class MailConfig {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;
    public MailConfig(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(texto);

        javaMailSender.send(message);
    }
}