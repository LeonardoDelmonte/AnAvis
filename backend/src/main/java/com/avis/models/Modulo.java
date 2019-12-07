package com.avis.models;

public class Modulo{

    private char sesso;
    private boolean fumatore,sportivo;

    public Modulo(){
        this.sesso='m';
        this.fumatore=true;
        this.sportivo=true;
    }

    

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public boolean isFumatore() {
        return fumatore;
    }

    public void setFumatore(boolean fumatore) {
        this.fumatore = fumatore;
    }

    public boolean isSportivo() {
        return sportivo;
    }

    public void setSportivo(boolean sportivo) {
        this.sportivo = sportivo;
    }
}