package com.moc.controllers.autenticazione;

import org.springframework.web.bind.annotation.RestController;

import com.moc.dto.RegistrazioneDto;
import com.moc.models.factory.Creator;
import com.moc.models.factory.CreatorCentroTrasfusione;
import com.moc.models.factory.CreatorDonatore;
import com.moc.models.factory.CreatorSedeAvis;
import com.moc.services.UtenteService;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseOK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Registrazione si occupa di registrare un utente qualsiasi delegando il compito di creazione ad una factory.
 * Registrazione si occupa di registrare un donatore , da parte di una sedeAvis, delegando il compito di creazione ad una factory.
 *
 * implementa l'interfaccia che ha le signature dei metodi e i suoi mapping
 * 
 * //TO DO//
 * randomizzare la pw
 * mandare una mail alla creazione del donatore
 * cambiare RegisterDto e vedere se si può fare qualcosa di meglio con il mapper
 * ??? ObjectMapper vs ModelMapper ???
 * 
 **/

@RestController
public class Registrazione implements RegistrazioneInterface {

    @Autowired
    UtenteService utenteService;

    @Override
    public ResponseEntity<InterfaceApi> registrazione(RegistrazioneDto registrazioneDto) throws NoSuchFieldException {

        Creator creator;

        if (registrazioneDto.getDonatore() != null) {
            creator = new CreatorDonatore();
        } else if (registrazioneDto.getSedeAvis() != null) {
            creator = new CreatorSedeAvis();
        } else if (registrazioneDto.getCentroTrasfusione() != null) {
            creator = new CreatorCentroTrasfusione();
        } else {
            throw new NoSuchFieldException("nessuna corrispondenza tra Json inviato e Json aspettato");
        }

        utenteService.registrazione(creator.creaUtente(registrazioneDto));

        return new ResponseEntity<InterfaceApi>(new ResponseOK("utente registrato correttamente"), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<InterfaceApi> registrazioneDonatore(RegistrazioneDto registrazioneDto) throws NoSuchFieldException {
        
        if(registrazioneDto.getDonatore()==null)
            throw new NullPointerException("argomento passato al metodo NULL");
        
        Creator creator = new CreatorDonatore();

        registrazioneDto.getDonatore().setPassword("passwordRNG");

        utenteService.registrazione(creator.creaUtente(registrazioneDto));

        //manda una mail alla mail appena registrata con la pw per accedere al proprio account

        return new ResponseEntity<InterfaceApi>(new ResponseOK("utente registrato correttamente"),HttpStatus.CREATED);
    }

}