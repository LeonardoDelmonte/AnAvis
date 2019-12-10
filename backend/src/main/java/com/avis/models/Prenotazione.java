package com.avis.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Prenotazione {

    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long idPrenotazione;
    @Column
    private long idDonatore;
    @Column
    private long idSedeAvis;
    @Column
    @NotEmpty
    private Date data;
    
}