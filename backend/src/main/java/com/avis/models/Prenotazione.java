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

import lombok.Data;

@Data
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

    public Prenotazione(@NotNull SedeAvis sedeAvis, @NotNull Timestamp date) {
        this.idSedeAvis = sedeAvis;
        this.date = date;
    }

}