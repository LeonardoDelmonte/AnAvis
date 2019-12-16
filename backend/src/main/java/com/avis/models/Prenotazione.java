package com.avis.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prenotazione {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPrenotazione;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDonatore", referencedColumnName = "id", nullable = false)
    private Donatore idDonatore;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSedeAvis", referencedColumnName = "id")
    private SedeAvis idSedeAvis;
    @Column
    private Date date;

    public Prenotazione() {

    }

    public Prenotazione(SedeAvis idSedeAvis) {
        this.idSedeAvis = idSedeAvis;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}