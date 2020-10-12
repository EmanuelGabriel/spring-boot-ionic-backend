package com.fmtz.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
//	Essa classe vai ser responsável por simular o envio de email no log do servidor
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		
	LOG.info("Simulando envio de email...");
	LOG.info(msg.toString());
	LOG.info("Email enviado");
		
	}
}
