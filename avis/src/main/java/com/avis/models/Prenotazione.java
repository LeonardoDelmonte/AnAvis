package com.avis.models;

import java.util.Calendar;
import java.util.GregorianCalendar;


//entity

public class Prenotazione {
    private long id;
    private Donatore donatore;
    private String sedeAvis;
    private GregorianCalendar data;

    public Prenotazione(String sedeAvis,GregorianCalendar data){

        this.sedeAvis=sedeAvis;
        this.data=data;
        
    }

    @Override
    public String toString(){
        String a = "";
        a+=sedeAvis+" "+data.getGregorianChange().toString();
        return a;
    }
}