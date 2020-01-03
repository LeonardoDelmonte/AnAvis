package com.avis.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Prenotazione {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPrenotazione;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDonatore", referencedColumnName = "id")
    private Donatore idDonatore;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSedeAvis", referencedColumnName = "id")
    @NotNull
    private SedeAvis idSedeAvis;
    @Column
    @NotNull
    private Timestamp date;

    public Prenotazione() {

    }

    public Prenotazione(SedeAvis sedeAvis, Timestamp date) {
        this.idSedeAvis = sedeAvis;
        this.date=date;
    }

    public long getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(long idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public Donatore getIdDonatore() {
        return idDonatore;
    }

    public void setIdDonatore(Donatore idDonatore) {
        this.idDonatore = idDonatore;
    }

    public SedeAvis getIdSedeAvis() {
        return idSedeAvis;
    }

    public void setIdSedeAvis(SedeAvis idSedeAvis) {
        this.idSedeAvis = idSedeAvis;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}