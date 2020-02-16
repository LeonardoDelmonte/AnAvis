package com.moc.services;

import java.util.List;

import com.moc.dto.CentroTrasfusioneProfiloDto;
import com.moc.models.CentroTrasfusione;
import com.moc.models.Emergenza;

public interface CentroTrasfusioneInterface {
    
    CentroTrasfusione findByEmail(String email);

	List<Emergenza> ottieniEmergenze(CentroTrasfusione centro);

	CentroTrasfusioneProfiloDto ottieniProfilo(CentroTrasfusione centro);

	void modificaProfilo(CentroTrasfusione centro, CentroTrasfusioneProfiloDto profilo);

}