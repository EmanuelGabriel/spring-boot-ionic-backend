package com.fmtz.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fmtz.cursomc.security.UserSS;

public class UserService {
	
	//RETORNA USUÁRIO LOGADO NO SISTEMA
	public static UserSS authenticated() {
	try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
	catch (Exception e) {
		return null;
		}
	}
}

