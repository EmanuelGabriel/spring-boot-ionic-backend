package com.fmtz.cursomc.services;

import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.fmtz.cursomc.domain.Cliente;
import com.fmtz.cursomc.domain.Pedido;

@Service
@Primary
public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
