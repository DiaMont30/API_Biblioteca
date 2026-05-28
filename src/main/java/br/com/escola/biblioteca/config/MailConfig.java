package br.com.escola.biblioteca.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailConfig {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public MailConfig(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String destinatario, String assunto, String nomeUsuario, String nomeLivro, String acao) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject(assunto);

            String corpoHtml = """
                    <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; border: 1px solid #ddd; border-radius: 8px; overflow: hidden;">
                        <div style="background-color: #2E8B57; color: white; padding: 20px; text-align: center;">
                            <h2 style="margin: 0;">Atualização no Acervo 📚</h2>
                        </div>
                        <div style="padding: 20px; color: #333;">
                            <p style="font-size: 16px;">Olá, <strong>%s</strong>!</p>
                            <p style="font-size: 16px; line-height: 1.5;">O status do livro <strong>'%s'</strong> foi alterado em nosso sistema.</p>

                            <div style="background-color: #f9f9f9; border-left: 4px solid #2E8B57; padding: 15px; margin: 20px 0;">
                                <p style="margin: 0; font-size: 16px;">Status da Ação: <strong>%s</strong></p>
                            </div>

                            <p style="font-size: 14px; color: #777;">Se você não realizou esta alteração, entre em contato com o suporte imediatamente.</p>
                        </div>
                        <div style="background-color: #f1f1f1; padding: 10px; text-align: center; font-size: 12px; color: #666;">
                            <p style="margin: 0;">Grupo 6 - Turma 34 © 2026</p>
                        </div>
                    </div>
                    """
                    .formatted(nomeUsuario, nomeLivro, acao);

            helper.setText(corpoHtml, true);

            javaMailSender.send(message);
            System.out.println("[EMAIL] Enviado com sucesso em HTML para: " + destinatario);

        } catch (MessagingException e) {
            System.out.println("[EMAIL] Falha ao enviar email HTML para " + destinatario + ": " + e.getMessage());
        }
    }
}