package com.dogeway.dw.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailAunthentication implements MailSender {
    JavaMailSender javaMailSender = null;



    public void emailAuthentication(String email){
        SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Código de Verificación - Dogeway");
        simpleMailMessage.setText("¡Gracias por registrarte en Dogeway!\n\n" +
                "Tu código de verificación es: " + 1 + "\n\n" +
                "Usa este código para confirmar tu cuenta. ¡Bienvenido a Dogeway!");

        send(simpleMailMessage);
    }

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }
}
