package com.avis.security;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.avis.security.dto.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

//filter custom che scatta prima di quelli di spring security 

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    //meccanismo di autenticazione di spring security
    @Autowired
    private UserDetailsService userDetailsService;

    //custom class che calcola e ritorna i valori del token
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //prende dal file propperties la stringa Header
    @Value("${jwt.header}")
    private String tokenHeader;


    //controlla se il token è valido
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //un client autenticato tiene il suo token nell'header della request, quindi qui lo vado a prendere 
        //per poi controllarlo ecc ecc 
        String authToken = request.getHeader(this.tokenHeader);

        //credo che questa classe andrà customizzzata prima o poi
        //UserDetails userDetails = null;
        JwtUser jwtUser = null;

        //se il token nella request esiste lo passo a tokenUtil che mi riotnra i dettagli contenuti al suo interno
        if(authToken != null){
            jwtUser = jwtTokenUtil.getUserDetails(authToken);
        }

        //credo controlli se il token nella request è ancora valido (perchè ha dei minuti di vita)
        if (jwtUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Ricostruisco l userdetails con i dati contenuti nel token


            // controllo integrita' token
            if (jwtTokenUtil.validateToken(authToken, jwtUser)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        //qui scatteranno i filtri di spring security sia se il token esiste sia che no????? .-.
        chain.doFilter(request, response);
    }
}