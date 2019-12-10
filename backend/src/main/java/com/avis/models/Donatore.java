package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Donatore{

    @Column
    @NotEmpty
    @OneToOne
    private long utenteId;
    @Column
    @NotEmpty
    private String nome,cognome;
    @Column
    @NotEmpty
    private byte abilitazioneDonazione;
    @Column
    private long modulo;

}