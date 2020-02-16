package com.moc.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//questa classe è un filter custom che scatta prima di quello standard di javaSecurity

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.header}")
	private String jwtHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		//controllo se cè il token nella richiesta
		String jwtToken = request.getHeader(jwtHeader);
		UserDetails utente = null;
		if (jwtToken!= null) {
			//se il token è presente creo gli userDetails
			utente = jwtTokenUtil.getUserDetails(jwtToken);			
		}
		//se ho gli userDetails e la sessione non esiste controllo la validità del token
		if (utente != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//validateToken
			if (jwtTokenUtil.validateToken(jwtToken)) {
				//token valido! setto l'authenticationToken e creo la sessione corrente
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                new UsernamePasswordAuthenticationToken(utente,null, utente.getAuthorities());           
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));						
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		//parte il filter di javaSecurity
		chain.doFilter(request, response);
	}
}