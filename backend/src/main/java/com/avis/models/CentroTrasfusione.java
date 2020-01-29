package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CentroTrasfusione extends Utente {

    private static final long serialVersionUID = 1L;
    @Column
    @NotNull
    private String regione, provincia, comune;
    @Column
    private String indirizzo, direttore, ospedale;

    public CentroTrasfusione() {
    }

    public CentroTrasfusione(@NotNull String email, @NotNull String password, @NotNull String ruolo,
            @NotNull String regione, @NotNull String provincia, @NotNull String comune) {
        super(email, password, ruolo);
        this.regione = regione;
        this.provincia = provincia;
        this.comune = comune;
    }


}