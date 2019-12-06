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

}