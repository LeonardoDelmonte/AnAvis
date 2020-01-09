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

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column
    private String nome, cognome;
    @Column
    private byte abilitazioneDonazione;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModulo", referencedColumnName = "id")
    private Modulo modulo;
    /*
     * @OneToMany(mappedBy = "idDonatore", fetch = FetchType.LAZY)
     * 
     * @JsonIgnore private List<Prenotazione> prenotazione;
     */

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

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    /*
     * public List<Prenotazione> getPrenotazione() { return prenotazione; }
     * 
     * public void setPrenotazione(List<Prenotazione> prenotazione) {
     * this.prenotazione = prenotazione; }
     */

}
