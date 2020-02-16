package com.moc.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Builder;
import lombok.Data;

/**
 * UtenteCorrente
 * implementa userDetails e ha la responsabilità di essere l'utente loggato in questo istante
 * per ora va bene, ma se pò fa meglio e direttamente con Utente forse anche se è abstract
 * quindi vedemo dopo
 */
@Data
@Builder
public class UtenteCorrente implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final long id;
    private final String ruolo,email,password;
    private final Collection<? extends GrantedAuthority> autorizzazioni;

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.autorizzazioni;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

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