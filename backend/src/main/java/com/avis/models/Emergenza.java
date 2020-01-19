package com.avis.models;

import java.sql.Timestamp;
import java.util.Date;

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
public class Emergenza {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCentroTrasfusione", referencedColumnName = "id")
    @NotNull
    private CentroTrasfusione idCentroTrasfusione;
    @Column
    @NotNull
    private String gruppoSanguigno;
    @Column
    private Timestamp dataEmergenza;

    public Emergenza() {

    }

    public Emergenza(@NotNull CentroTrasfusione centro, @NotNull String gruppo) {
        this.idCentroTrasfusione = centro;
        this.gruppoSanguigno = gruppo;
        Date date = new Date();
        this.dataEmergenza = new Timestamp(date.getTime());
    }

}