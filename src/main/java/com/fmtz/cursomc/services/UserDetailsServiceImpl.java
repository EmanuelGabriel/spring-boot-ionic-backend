package com.fmtz.cursomc.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fmtz.cursomc.domain.Cliente;
import com.fmtz.cursomc.repositories.ClienteRepository;
import com.fmtz.cursomc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente cli = repo.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}
