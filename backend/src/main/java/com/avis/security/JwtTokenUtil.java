package com.avis.security;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import com.avis.dto.JwtUser;
import org.springframework.beans.factory.annotation.Value;
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
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Long getIdFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return Long.valueOf((Integer)claims.get("id"));
	}

	public String getAuthoritiesFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return (String)claims.get("roles");
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(JwtUser userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id",userDetails.getID());
		claims.put("roles",userDetails.getRuolo());		
		return doGenerateToken(claims, userDetails.getUsername());
	}

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(Map<String, Object> claims, String email) {
		return Jwts.builder().setClaims(claims).setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	
	public Boolean validateToken(String token, JwtUser userDetails) {
		final String email = getEmailFromToken(token);
		return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
	}


	public JwtUser getUserDetails(String token) {
        if(token == null){
            return null;
        }
        try {
            return new JwtUser(
                    getIdFromToken(token),
					getEmailFromToken(token), "",
					getAuthoritiesFromToken(token)					               
            );
        } catch (Exception e) {
            return null;
        }

	}


	//private Claims getClaimsFromToken(String token) {
    //    Claims claims;
    //    try {
    //        claims = Jwts.parser()
    //                .setSigningKey(secret)
    //                .parseClaimsJws(token)
    //                .getBody();
    //    } catch (Exception e) {
    //        claims = null;
    //    }
    //    return claims;
    //}

}
