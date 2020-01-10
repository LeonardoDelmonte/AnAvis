package com.avis.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utente implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotNull
    private String email;
    @Column
    @NotNull
    private String password, ruolo;

    public Utente() {
    }

    public Utente(String email, String pw, String ruolo) {
        this.email = email;
        this.password = pw;
        this.ruolo = ruolo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPw(String pw) {
        this.password = pw;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authoritiesDonatore = new ArrayList<>();

        authoritiesDonatore.add(new SimpleGrantedAuthority("profilo"));
        switch (ruolo) {
        case "donatore":
            authoritiesDonatore.add(new SimpleGrantedAuthority("donare"));
            return authoritiesDonatore;
        case "sedeAvis":
            authoritiesDonatore.add(new SimpleGrantedAuthority("donare"));
            authoritiesDonatore.add(new SimpleGrantedAuthority("handlerDate"));
            return authoritiesDonatore;
        case "centroTrasfusioni":
            authoritiesDonatore.add(new SimpleGrantedAuthority("requestEmerg"));
            return authoritiesDonatore;
        }
        return authoritiesDonatore;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return "";
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}