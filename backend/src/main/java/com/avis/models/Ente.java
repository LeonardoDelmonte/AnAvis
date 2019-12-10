package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Ente{

    @Column
    @NotEmpty
    private long utenteId;
    @Column
    @NotEmpty
    private String regione,provincia,comune,denominazione;
    
}