package com.avis.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Ente{

    @Column
    @NotEmpty
    private long utenteId;
    @Column
    @NotEmpty
    private String regione,provincia,comune,denominazione;

    public String getRegione(){
        return regione;
    }

	public String getProvincia() {
		return null;
	}

	public String getComuni() {
		return null;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public long getId() {
		return utenteId;
	}
    
}