package com.avis.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.avis.models.Utente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class JwtUser implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final Long ID;
    private final String email,password;
    private final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    //voglio mettere più ruoli quindi userò la Collection!
    public JwtUser(Long ID, String email, String password, String ruolo) {
        this.ID = ID;
        this.email = email;
        this.password=password;
        this.authorities.add(new SimpleGrantedAuthority(ruolo));
    }

    public JwtUser(Utente utente){
        this.ID = utente.getId();
        this.email = utente.getEmail();
        this.password=utente.getPassword();
        this.authorities.add(new SimpleGrantedAuthority(utente.getRuolo()));
    }

    public String getEmail() {
        return email;
    }

    public String getRuolo() {
        return authorities.get(0).toString();
    }

    public Long getID() {
        return ID;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
