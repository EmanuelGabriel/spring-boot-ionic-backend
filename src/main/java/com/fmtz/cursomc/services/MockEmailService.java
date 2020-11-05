package com.fmtz.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.fmtz.cursomc.domain.Cliente;

@Service
public class MockEmailService extends AbstractEmailService {
//	Essa classe vai ser responsável por simular o envio de email no log do servidor
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		
	LOG.info("Simulando envio de email...");
	LOG.info(msg.toString());
	LOG.info("Email enviado");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
	LOG.info("Simulando envio de email HTML...");
	LOG.info(msg.toString());
	LOG.info("Email enviado");
			
	}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		// TODO Auto-generated method stub
		
	}
}
