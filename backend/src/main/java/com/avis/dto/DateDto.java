package com.avis.dto;
import java.sql.Timestamp;

public class DateDto {

    private Timestamp dataIniziale;
    private Timestamp dataFinale;
    private String comune;
    
    public Timestamp getDataIniziale() {
        return dataIniziale;
    }

    public void setDataIniziale(Timestamp dataIniziale) {
        this.dataIniziale = dataIniziale;
    }

    public Timestamp getDataFinale() {
        return dataFinale;
    }

    public void setDataFinale(Timestamp dataFinale) {
        this.dataFinale = dataFinale;
    }

    public DateDto(Timestamp dataIniziale, Timestamp dataFinale) {
        this.dataIniziale = dataIniziale;
        this.dataFinale = dataFinale;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }
}