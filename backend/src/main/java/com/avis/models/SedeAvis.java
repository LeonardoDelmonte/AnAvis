package com.avis.models;
import java.util.List;

public class SedeAvis {

    
    private long id;
    private String provincia,comune,indirizzo,denominazione;
    private List<Prenotazione> calendario;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public List<Prenotazione> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<Prenotazione> calendario) {
        this.calendario = calendario;
    }
}