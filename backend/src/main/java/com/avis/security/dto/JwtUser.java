package com.avis.security.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.avis.models.Utente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String email, password, username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public JwtUser(
            String email,
            String password, 
            String username,
            Collection<? extends GrantedAuthority> authorities,
            Boolean enabled
    ) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
        this.enabled = enabled;
    }


    public JwtUser(Utente utente) {
        this.email = utente.getEmail();
        this.password = utente.getPw();
        this.username = utente.getEmail();
        List<SimpleGrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(utente.getRuolo()));
        this.authorities=auth;
        this.enabled = true;
	}


	@Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
