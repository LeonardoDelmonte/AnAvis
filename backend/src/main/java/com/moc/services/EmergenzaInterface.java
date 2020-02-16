package com.moc.services;

import java.util.List;

import com.moc.models.CentroTrasfusione;
import com.moc.models.Emergenza;

public interface EmergenzaInterface {

	Emergenza findById(Long idEmergenza);

	void cancellaEmergenza(Emergenza emergenza);

	void inserireEmergenza(String gruppo, CentroTrasfusione centro);

	List<?> contaEmergenze();

}