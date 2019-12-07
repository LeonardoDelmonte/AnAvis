package com.avis.models;

public class Donatore{
    private long id;
    private String nome,cognome;
    private int eta;
    private Modulo modulo;
    
    public Donatore(String nome,String cognome,int eta,Modulo modulo){
        this.nome=nome;
        this.cognome=cognome;
        this.eta=eta;
        this.modulo=modulo;
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

}