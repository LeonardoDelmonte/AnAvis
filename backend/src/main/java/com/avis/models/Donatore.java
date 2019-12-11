package com.avis.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Donatore extends Utente {
    // ho messo extends solo per fare un services in meno, se non funziona si toglie
    @Column
    private String nome, cognome;
    @Column
    private byte abilitazioneDonazione;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModulo", referencedColumnName = "id")
    private Modulo modulo;

    public Donatore() {
    }

    public Donatore(String email, String pw, String ruolo, String nome, String cognome) {
        super(email, pw, ruolo);
        this.nome = nome;
        this.cognome = cognome;
        this.abilitazioneDonazione = 0;
    }

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

}
