package com.avis.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CentroTrasfusione extends Utente {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column
    private String regione, provincia, comune, indirizzo, denominazione;
    @OneToMany(mappedBy = "idCentroTrasfusione", fetch = FetchType.LAZY)
    private List<Emergenza> emergenze;

    public CentroTrasfusione() {
    }

    public CentroTrasfusione(String email, String pw, String ruolo) {
        super(email, pw, ruolo);
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
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

}