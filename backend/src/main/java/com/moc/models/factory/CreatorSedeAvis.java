package com.moc.models.factory;

import com.moc.dto.RegistrazioneDto;
import com.moc.models.Autorizzazioni;
import com.moc.models.SedeAvis;
import com.moc.models.Utente;

/**
 * CreatorSedeAvis
 * 
 */
public class CreatorSedeAvis extends Creator {
    
    @Override
    public Utente creaUtente(RegistrazioneDto registrazioneDto) {
        SedeAvis sedeAvis = registrazioneDto.getSedeAvis();
        controllaInfoUtente(sedeAvis);
        setAutorizzazioni(sedeAvis);
        return this.builder(sedeAvis);
    }

    @Override
    protected void setAutorizzazioni(Utente sedeAvis) {
        Autorizzazioni autorizzazioni = new Autorizzazioni();
        autorizzazioni.autorizzazioniSedeAvis();
        sedeAvis.setAutorizzazioni(autorizzazioni.getAutorizzazioni());
    }

    private Utente builder(SedeAvis sedeAvis){
        //lascio il builder e non ritorno la variabile locale 
        //perchè cosi un json con qualcosa in più ad esempio un modulo già creato
        //non può passare
        return   
            SedeAvis.builder()
                .email(sedeAvis.getEmail())
                .password(sedeAvis.getPassword())
                .ruolo(sedeAvis.getRuolo())
                .regione(sedeAvis.getRegione())
                .provincia(sedeAvis.getProvincia())
                .comune(sedeAvis.getComune())
                .autorizzazioni(sedeAvis.getAutorizzazioni())
                //qui creeremo altre relationShip come la lista di date libere
                .build(); 
    }

	
	

    
}