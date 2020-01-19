package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Modulo {

    @Column
    @Id
    private Long id;
    @Column
    @NotNull
    private String gruppoSanguigno, fumatore;

    public Modulo() {
    }

    public Modulo(@NotNull String gruppoSanguigno, @NotNull String fumatore) {
        this.gruppoSanguigno = gruppoSanguigno;
        this.fumatore = fumatore;
    }

}