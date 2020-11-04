package com.fmtz.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	//DECLARAÇÃO DOS ENDPOINTS LIBERADOS POR PADRÃO NO VETOR A SEGUIR:
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	//DECLARAÇÃO DOS ENDPOINTS LIBERADOS POR PADRÃO (SOMENTE LEITURA):
		private static final String[] PUBLIC_MATCHERS_GET = {
				"/produtos/**",
				"/categorias/**",
				"/clientes/**"
		};
	
	//DEFINIÇÃO DOS ENDPOINTS AUTORIZADOS POR PADRÃO E COM AUTENTICAÇÃO
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//LIBERA ACESSO AO BANCO DE TEST H2
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable(); //Como a aplicação não irá armazenar sessão, a proteção contra ataques CSRF é desnecessária
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Assegura que a aplicação não criará sessões de usuário
	}
	
	
	//PERMISSÃO DE ACESSO BÁSICO DE MULTIPLAS FONTES PARA TODOS OS ENDPOINTS (Para fins de tests e desenvolvimento)
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	} 

}
