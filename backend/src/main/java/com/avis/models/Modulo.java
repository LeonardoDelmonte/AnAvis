package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Modulo{

    @Column
    @NotEmpty
    private long id;
    @Column
    private byte sesso,fumatore,sportivo;
    @Column
    private String gruppoSanguigno;

}