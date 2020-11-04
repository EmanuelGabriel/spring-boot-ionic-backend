package com.fmtz.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fmtz.cursomc.security.JWTAuthenticationFilter;
import com.fmtz.cursomc.security.JWTUtil;
//import com.fmtz.cursomc.security.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
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
		
		private static final String[] PUBLIC_MATCHERS_POST = {
				"/clientes/**",
				"/auth/forgot/**"
		};
	
	//DEFINIÇÃO DOS ENDPOINTS AUTORIZADOS POR PADRÃO E COM AUTENTICAÇÃO
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
	            http.headers().frameOptions().disable();
	        }
			
			http.cors().and().csrf().disable();
			http.authorizeRequests()
				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated();
			http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
//			http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
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
