package com.api.coursesmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmailWithFile(ByteArrayOutputStream file) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        InputStreamSource attachment = new ByteArrayResource(file.toByteArray());

        helper.setFrom("joaosipauz@gmail.com");
        helper.setTo("joaosipauba@hotmail.com");
        helper.setSubject("Relátorio Automático");
        helper.setText("Esse é um email automático, favor não responder.");
        helper.addAttachment("Relatorio.pdf", attachment);

        emailSender.send(message);
    }
}
