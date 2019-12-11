package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class Modulo{

    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(mappedBy = "Modulo")
    private Donatore donatore;
    @Column
    private byte sesso,fumatore,sportivo;
    @Column
    private String gruppoSanguigno;

}