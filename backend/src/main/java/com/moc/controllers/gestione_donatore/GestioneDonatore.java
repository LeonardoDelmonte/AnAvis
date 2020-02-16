package com.moc.controllers.gestione_donatore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.moc.models.Donatore;
import com.moc.models.Prenotazione;
import com.moc.security.UtenteCorrente;
import com.moc.services.DonatoreInterface;
import com.moc.services.PrenotazioneInterface;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseCustomEntity;
import com.moc.utils.ResponseOK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * GestioneDonatore
 */
@RestController
public class GestioneDonatore implements GestioneDonatoreInterface {

    @Autowired
    private DonatoreInterface donatoreService;
    @Autowired
    private PrenotazioneInterface prenotazioneService;

    @Override
    public ResponseEntity<InterfaceApi> ottieniDate(UtenteCorrente utenteCorrente) {
        Donatore donatore = donatoreService.findByEmail(utenteCorrente.getEmail());
        List<Prenotazione> list = donatoreService.ottieniPrenotazioni(donatore);
        return new ResponseEntity<>(new ResponseCustomEntity<List<Prenotazione>>(list), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> eliminareData(UtenteCorrente utenteCorrente, HttpServletRequest req) {
        Long idPrenotazione = Long.valueOf(req.getHeader("data"));
        Prenotazione prenotazione = prenotazioneService.findById(idPrenotazione);
        prenotazioneService.cancellaPrenotazione(prenotazione);
        return new ResponseEntity<>(new ResponseOK("Prenotazione annullata con successo"), HttpStatus.OK);
    }

}