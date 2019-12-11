package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Modulo{

    @Column
    @Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    private int id;
    @Column 
    private String gruppoSanguigno, fumatore;
    @OneToOne(mappedBy = "modulo")
    private Donatore donatore;

    public Modulo(){

    }

    public Modulo(String gruppoSanguigno, String fumatore) {
        this.gruppoSanguigno = gruppoSanguigno;
        this.fumatore = fumatore;
    }

    public String getGruppoSanguigno() {
        return gruppoSanguigno;
    }

    public void setGruppoSanguigno(String gruppoSanguigno) {
        this.gruppoSanguigno = gruppoSanguigno;
    }

    public String getFumatore() {
        return fumatore;
    }

    public void setFumatore(String fumatore) {
        this.fumatore = fumatore;
    }

}