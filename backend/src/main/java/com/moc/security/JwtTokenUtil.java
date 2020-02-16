package com.moc.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//questa classe si occupa di creare/scomporre un token 

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;
	@Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
	private int JWT_TOKEN_VALIDITY;

	public String getEmailFromToken(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	public Collection<SimpleGrantedAuthority> getAuthFromToken(String token) {
		
		return Arrays.stream(getAllClaimsFromToken(token).get("auth").toString().split(","))
		.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
	}

	public String getRoleFromToken(String token) {
		return getAllClaimsFromToken(token).getAudience();
	}

	public Long getIdFromToken(String token) {
		return Long.valueOf(getAllClaimsFromToken(token).getId());
	}

	public Date getExpirationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UtenteCorrente utente) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("jti",utente.getId());
		claims.put("sub", utente.getEmail());
		claims.put("aud", utente.getRuolo());
		claims.put("auth",utente.getAutorizzazioni()
							.stream()
							.map(GrantedAuthority::getAuthority)
							.collect(Collectors.joining(",")));
		return doGenerateToken(claims);
	}

	private String doGenerateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public Boolean validateToken(String token) {
		return (!isTokenExpired(token));
	}

	public UserDetails getUserDetails(String token){
		try{
			return UtenteCorrente.builder()
								.id(getIdFromToken(token))
								.email(getEmailFromToken(token))
								.ruolo(getRoleFromToken(token))
								.autorizzazioni(getAuthFromToken(token))
								.build();
		}catch(Exception e){
			return null;
		}
	}

}
