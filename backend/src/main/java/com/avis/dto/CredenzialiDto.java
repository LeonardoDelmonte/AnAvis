package com.avis.dto;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.SedeAvis;

public class CredenzialiDto {

    private Donatore donatore;
    private CentroTrasfusione centro;
    private SedeAvis sede;

    public Donatore getDonatore() {
        return donatore;
    }

    public void setDonatore(Donatore donatore) {
        this.donatore = donatore;
    }

    public CentroTrasfusione getCentro() {
        return centro;
    }

    public void setCentro(CentroTrasfusione centro) {
        this.centro = centro;
    }

    public SedeAvis getSede() {
        return sede;
    }

    public void setSede(SedeAvis sede) {
        this.sede = sede;
    }

}
    
