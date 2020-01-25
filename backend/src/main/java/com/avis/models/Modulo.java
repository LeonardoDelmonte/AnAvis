package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Modulo {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String codiceFiscale, indirizzo, telefono, gruppoSanguigno, fumatore, malattie, vaccinazioni, allergie,
            farmaci;

    public Modulo() {
    }

    public Modulo(@NotNull String codiceFiscale, @NotNull String indirizzo, @NotNull String telefono,
            @NotNull String gruppoSanguigno, @NotNull String fumatore, @NotNull String malattie,
            @NotNull String vaccinazioni, @NotNull String allergie, @NotNull String farmaci) {
        this.codiceFiscale = codiceFiscale;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.gruppoSanguigno = gruppoSanguigno;
        this.fumatore = fumatore;
        this.malattie = malattie;
        this.vaccinazioni = vaccinazioni;
        this.allergie = allergie;
        this.farmaci = farmaci;
    }

}