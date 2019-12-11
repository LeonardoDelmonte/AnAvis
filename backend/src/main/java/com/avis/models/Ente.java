package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;

@Entity
@PrimaryKeyJoinColumn(name="idUtente")
public class Ente extends Utente{

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
    
}