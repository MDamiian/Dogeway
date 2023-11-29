package com.dogeway.dw.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/verifier")
public class EmailAuthentication {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailAuthentication(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);

        try {
            javaMailSender.send(mensaje);
            System.out.println("Correo electrónico enviado a: " + destinatario);
        } catch (MailException e) {
            System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
            // Aquí puedes manejar la excepción de acuerdo a tus necesidades
        }
    }


}



