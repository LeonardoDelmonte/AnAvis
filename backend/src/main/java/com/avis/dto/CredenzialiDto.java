package com.avis.dto;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.SedeAvis;

public class CredenzialiDto {

    private Donatore donatore;
    private CentroTrasfusione centroTrasfusione;
    private SedeAvis sedeAvis;


    public Donatore getDonatore() {
        return donatore;
    }

    public void setDonatore(Donatore donatore) {
        this.donatore = donatore;
    }

    public CentroTrasfusione getCentroTrasfusione() {
        return centroTrasfusione;
    }

    public void setCentroTrasfusione(CentroTrasfusione centroTrasfusione) {
        this.centroTrasfusione = centroTrasfusione;
    }

    public SedeAvis getSedeAvis() {
        return sedeAvis;
    }

    public void setSedeAvis(SedeAvis sedeAvis) {
        this.sedeAvis = sedeAvis;
    }



}
    
