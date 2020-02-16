package com.moc.controllers.gestione_prenotazione;

import java.util.List;
import java.util.Set;

import com.moc.components.ControllaDate;
import com.moc.dto.DtoCercaDateLibere;
import com.moc.dto.PrenotazionePerTerziDto;
import com.moc.models.Donatore;
import com.moc.models.Prenotazione;
import com.moc.models.SedeAvis;
import com.moc.security.UtenteCorrente;
import com.moc.services.DonatoreInterface;
import com.moc.services.PrenotazioneInterface;
import com.moc.services.SedeAvisInterface;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseCustomEntity;
import com.moc.utils.ResponseOK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * GestionePrenotazione
 */
@RestController
public class GestionePrenotazione implements GestionePrenotazioneInterface {

    @Autowired
    private DonatoreInterface donatoreService;
    @Autowired
    private PrenotazioneInterface prenotazioneService;
    @Autowired
    private SedeAvisInterface sedeAvisService;
    @Autowired
    private ControllaDate controllaDate;

    @Override
    public ResponseEntity<InterfaceApi> prenotaPerTerzi(UtenteCorrente utenteCorrente, PrenotazionePerTerziDto dto) {
        prenota(dto.getEmail(), dto.getIdPrenotazione());
        return new ResponseEntity<>(new ResponseOK("Prenotazione effettuata"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> prenotaData(UtenteCorrente utenteCorrente, Long idPrenotazione) {
        prenota(utenteCorrente.getEmail(), idPrenotazione);
        return new ResponseEntity<>(new ResponseOK("Prenotazione effettuata"), HttpStatus.OK);
    }

    private Prenotazione prenota(String email, Long idPrenotazione) {
        Donatore donatore = donatoreService.findByEmail(email);
        donatoreService.isModuloCompilato(donatore);
        donatoreService.isAbilitatoAdonare(donatore);
        Prenotazione prenotazione = prenotazioneService.findById(idPrenotazione);
        prenotazione = prenotazioneService.prenotaData(donatore, prenotazione);
        donatoreService.setAbilitazioneFalse(donatore);
        return prenotazione;
    }

    @Override
    public ResponseEntity<InterfaceApi> ottieniDateLibere(UtenteCorrente utenteCorrente, DtoCercaDateLibere dto) {
        SedeAvis sedeAvis = sedeAvisService.findByComune(dto.getComune());
        if (!controllaDate.controllaCorrettezzaDate(dto.getRangeDateDto()))
            throw new IllegalArgumentException("Date inserite non valide");
        List<Prenotazione> list = prenotazioneService.getDateLibere(sedeAvis, dto.getRangeDateDto());
        return new ResponseEntity<>(new ResponseCustomEntity<List<Prenotazione>>(list), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> ottieniRegioni(UtenteCorrente utenteCorrente) {
        Set<String> set = sedeAvisService.getAllRegioni();
        return new ResponseEntity<>(new ResponseCustomEntity<Set<String>>(set), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> ottieniProvincie(UtenteCorrente utenteCorrente, String string) {
        Set<String> set = sedeAvisService.getAllProvince(string);
        return new ResponseEntity<>(new ResponseCustomEntity<Set<String>>(set), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> ottieniComuni(UtenteCorrente utenteCorrente, String string) {
        Set<String> set = sedeAvisService.getComune(string);
        return new ResponseEntity<>(new ResponseCustomEntity<Set<String>>(set), HttpStatus.OK);
    }

}