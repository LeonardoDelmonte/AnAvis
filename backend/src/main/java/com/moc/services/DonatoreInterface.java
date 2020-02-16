package com.moc.services;
import java.util.List;

import com.moc.dto.DonatoreProfiloDto;
import com.moc.models.Donatore;
import com.moc.models.Modulo;
import com.moc.models.Prenotazione;

/**
 * DonatoreInterface
 */
public interface DonatoreInterface {
    
    void modificaProfilo(Donatore donatore,DonatoreProfiloDto profilo);
    List<Prenotazione> ottieniPrenotazioni(Donatore donatore);
    Donatore findByEmail(String email);
	void modificaModulo(Donatore donatore, Modulo modulo);
    Modulo ottieniModulo(Donatore donatore);
    void isAbilitatoAdonare(Donatore donatore);
    void isModuloCompilato(Donatore donatore);
	DonatoreProfiloDto ottieniProfilo(Donatore donatore);
	void setAbilitazioneFalse(Donatore donatore);

}