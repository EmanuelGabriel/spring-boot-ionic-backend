package com.fmtz.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.fmtz.cursomc.domain.Cliente;
import com.fmtz.cursomc.domain.Pedido;

@Service
@Primary
public interface EmailService {
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
