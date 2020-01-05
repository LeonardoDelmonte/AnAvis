package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Modulo {

	@Column
    @Id    
    private Long id;
    @Column
    private String gruppoSanguigno, fumatore;
    @OneToOne(mappedBy = "modulo")
    private Donatore donatore;

    public Modulo() {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Donatore getDonatore() {
        return donatore;
    }

    public void setDonatore(Donatore donatore) {
        this.donatore = donatore;
    }

}