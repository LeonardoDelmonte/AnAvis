package com.moc.controllers.gestione_profilo;

import com.moc.dto.CentroTrasfusioneProfiloDto;
import com.moc.dto.DonatoreProfiloDto;
import com.moc.dto.SedeAvisProfiloDto;
import com.moc.models.CentroTrasfusione;
import com.moc.models.Donatore;
import com.moc.models.SedeAvis;
import com.moc.security.UtenteCorrente;
import com.moc.services.CentroTrasfusioneInterface;
import com.moc.services.DonatoreInterface;
import com.moc.services.SedeAvisInterface;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseCustomEntity;
import com.moc.utils.ResponseOK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * GestioneProfilo
 */
@RestController
public class GestioneProfilo implements GestioneProfiloInterface {

    @Autowired
    private DonatoreInterface donatoreService;
    @Autowired
    private SedeAvisInterface sedeAvisService;
    @Autowired
    private CentroTrasfusioneInterface centroTrasfusioneService;

    @Override
    public ResponseEntity<InterfaceApi> modificareProfiloDonatore(UtenteCorrente utenteCorrente,
            DonatoreProfiloDto profilo) {
        Donatore donatore = donatoreService.findByEmail(utenteCorrente.getEmail());
        donatoreService.modificaProfilo(donatore, profilo);
        return new ResponseEntity<>(new ResponseOK("Profilo modificato con successo"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> modificareProfiloSedeAvis(UtenteCorrente utenteCorrente,
            SedeAvisProfiloDto profilo) {
        SedeAvis sedeAvis = sedeAvisService.findByEmail(utenteCorrente.getEmail());
        sedeAvisService.modificaProfilo(sedeAvis, profilo);
        return new ResponseEntity<>(new ResponseOK("Profilo modificato con successo"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> modificareProfiloCentroTrasfusione(UtenteCorrente utenteCorrente,
            CentroTrasfusioneProfiloDto profilo) {
        CentroTrasfusione centro = centroTrasfusioneService.findByEmail(utenteCorrente.getEmail());
        centroTrasfusioneService.modificaProfilo(centro, profilo);
        return new ResponseEntity<>(new ResponseOK("Profilo modificato con successo"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> ottenereProfiloDonatore(UtenteCorrente utenteCorrente) {
        Donatore donatore = donatoreService.findByEmail(utenteCorrente.getEmail());
        if (donatore.getModulo() != null) {
            donatoreService.isAbilitatoAdonare(donatore);
        }
        DonatoreProfiloDto profilo = donatoreService.ottieniProfilo(donatore);
        return new ResponseEntity<>(new ResponseCustomEntity<DonatoreProfiloDto>(profilo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> ottenereProfiloSedeAvis(UtenteCorrente utenteCorrente) {
        SedeAvis sedeAvis = sedeAvisService.findByEmail(utenteCorrente.getEmail());
        SedeAvisProfiloDto profilo = sedeAvisService.ottieniProfilo(sedeAvis);
        return new ResponseEntity<>(new ResponseCustomEntity<SedeAvisProfiloDto>(profilo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> ottenereProfiloCentroTrasfusione(UtenteCorrente utenteCorrente) {
        CentroTrasfusione centro = centroTrasfusioneService.findByEmail(utenteCorrente.getEmail());
        CentroTrasfusioneProfiloDto profilo = centroTrasfusioneService.ottieniProfilo(centro);
        return new ResponseEntity<>(new ResponseCustomEntity<CentroTrasfusioneProfiloDto>(profilo), HttpStatus.OK);
    }

}