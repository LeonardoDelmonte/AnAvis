package com.avis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Utente{
    @Column
    @NotEmpty
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column
    @NotEmpty
    private String email,password;
    @Column
    private byte flagDonatore,flagSedeAvis,flagCentroTrasfusione;


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return null;
	}

}
