package com.moc.models.factory;

import com.moc.dto.RegistrazioneDto;
import com.moc.models.Autorizzazioni;
import com.moc.models.Donatore;
import com.moc.models.Utente;

/**
 * CreatorDonatore
 */
public class CreatorDonatore extends Creator{

	@Override
	public Utente creaUtente(RegistrazioneDto registrazioneDto) {
        Donatore donatore = registrazioneDto.getDonatore();
        this.controllaInfoUtente(donatore);
        this.setAutorizzazioni(donatore);
        return this.builder(donatore);
    }

    @Override
    protected void setAutorizzazioni(Utente donatore) {
        Autorizzazioni autorizzazioni = new Autorizzazioni();
        autorizzazioni.autorizzazioniDonatore();
        donatore.setAutorizzazioni(autorizzazioni.getAutorizzazioni());
    }

    private Utente builder(Donatore donatore){
        //lascio il builder e non ritorno la variabile locale 
        //perchè cosi un json con qualcosa in più ad esempio un modulo già creato
        //non può passare
        
        return   
            Donatore.builder()
                .email(donatore.getEmail())
                .password(donatore.getPassword())
                .ruolo(donatore.getRuolo())
                .nome(donatore.getNome())
                .cognome(donatore.getCognome())
                .autorizzazioni(donatore.getAutorizzazioni())
                .abilitaDonazione(false)
                .build(); 
    }


}
        
