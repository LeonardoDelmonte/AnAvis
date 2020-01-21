package com.avis.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Donatore extends Utente {

    private static final long serialVersionUID = 1L;
    @Column
    @NotNull
    private String nome, cognome;
    @Column
    private byte abilitazioneDonazione = 0;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModulo", referencedColumnName = "id")
    private Modulo modulo;

    public Donatore() {
    }

    public Donatore(String email, String pw, String ruolo, @NotNull String nome, @NotNull String cognome) {
        super(email, pw, ruolo);
        this.nome = nome;
        this.cognome = cognome;
    }

}
