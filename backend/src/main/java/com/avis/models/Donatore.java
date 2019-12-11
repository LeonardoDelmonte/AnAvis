package com.avis.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;

@Entity
@PrimaryKeyJoinColumn(name="idUtente")
public class Donatore extends Utente{

    @Column
    @NotEmpty
    private String nome,cognome;
    @Column
    @NotEmpty
    private byte abilitazioneDonazione;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModulo", referencedColumnName = "id")
    private Modulo modulo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public byte getAbilitazioneDonazione() {
        return abilitazioneDonazione;
    }

    public void setAbilitazioneDonazione(byte abilitazioneDonazione) {
        this.abilitazioneDonazione = abilitazioneDonazione;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

}