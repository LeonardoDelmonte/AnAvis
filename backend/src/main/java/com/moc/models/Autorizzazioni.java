package com.moc.models;

import java.util.ArrayList;


import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Autorizzazioni
 */
public class Autorizzazioni {

    private final ArrayList<SimpleGrantedAuthority> autorizzazioni;

    public Autorizzazioni(){
        this.autorizzazioni = new ArrayList<>();
        this.autorizzazioni.add(new SimpleGrantedAuthority("vedi-richieste"));
    }

    public void autorizzazioniDonatore(){
        this.autorizzazioni.add(new SimpleGrantedAuthority("donatore"));
        this.autorizzazioni.add(new SimpleGrantedAuthority("prenotazione"));
    }
    public void autorizzazioniSedeAvis(){
        this.autorizzazioni.add(new SimpleGrantedAuthority("sedeAvis"));
        this.autorizzazioni.add(new SimpleGrantedAuthority("prenotazione"));
    }
    public void autorizzazioniCentroTrasfusione(){
        this.autorizzazioni.add(new SimpleGrantedAuthority("centroTrasfusione"));
    }

    public ArrayList<SimpleGrantedAuthority> getAutorizzazioni(){
        return this.autorizzazioni;
    }

}