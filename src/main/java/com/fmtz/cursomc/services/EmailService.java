package com.fmtz.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.fmtz.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
