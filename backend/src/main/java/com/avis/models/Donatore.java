package com.avis.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Donatore extends Utente {

    @Column
    private String nome, cognome;
    @Column
    private byte abilitazioneDonazione;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModulo", referencedColumnName = "id")
    private Modulo modulo;
    @OneToMany(mappedBy = "idDonatore", fetch = FetchType.LAZY)
    private List<Prenotazione> prenotazione;

    public Donatore() {
    }

    public Donatore(String email, String pw, String ruolo) {
        super(email, pw, ruolo);
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
