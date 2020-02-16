package com.moc.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.moc.dto.RangeDateDto;
import com.moc.models.Donatore;
import com.moc.models.Prenotazione;
import com.moc.models.SedeAvis;

/**
 * PrenotazioneInterface
 */
public interface PrenotazioneInterface {

    Map<String,List<Timestamp>> salvaListaDate(List<Timestamp> list,SedeAvis sedeAvis);

    Prenotazione findById(Long id);

    Prenotazione prenotaData(Donatore donatore,Prenotazione prenotazione);

	void eliminaData(Prenotazione prenotazione);

	void cancellaPrenotazione(Prenotazione prenotazione);

	List<Prenotazione> getDateLibere(SedeAvis sedeAvis, RangeDateDto rangeDateDto);
}