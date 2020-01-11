package com.avis.security;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.avis.models.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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

	public String getRoleFromToken(String token) {
		return getAllClaimsFromToken(token).getAudience();
	}

	public Long getIdFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return Long.valueOf((Integer)claims.get("id"));
	}

	public List<SimpleGrantedAuthority>  getAuthoritiesFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		List<SimpleGrantedAuthority> authorities = null;
		if (claims.get("roles") != null) {
			authorities = ((List<?>) claims.get("roles")).stream()
			.map(role-> new SimpleGrantedAuthority((String) role)).collect(Collectors.toList());
		}
		return authorities;
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

	public String generateToken(Utente utente) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id",utente.getId());
		claims.put("sub", utente.getEmail());
		claims.put("aud", utente.getRuolo());
        claims.put("roles", utente.getAuthorities()); 
		return doGenerateToken(claims);
	}

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	
	public Boolean validateToken(String token, Utente userDetails) {
		final String email = getEmailFromToken(token);
		return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
	}


	public Utente getUserDetails(String token) {
        if(token == null){
            return null;
        }
        try {
            Utente u = new Utente(
				getEmailFromToken(token),"",
				getRoleFromToken(token));				               
			u.setId(getIdFromToken(token));			
			return u;
        } catch (Exception e) {
            return null;
        }
	}
}
