package com.fmtz.cursomc.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.fmtz.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}") //permite acessar o emissor padrão de emails que defini para a aplicação
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail()); //enviar para ...
		sm.setFrom(sender); //quem vai enviar ...
		sm.setSubject("Pedido confirmado! Código: " + obj.getId()); //Assunto do email
		sm.setSentDate(new Date(System.currentTimeMillis())); //hora de emissão do email
		sm.setText(obj.toString()); // Nesse caso o corpo padrão do email vai ser o pedido
		return sm;
	}
}
