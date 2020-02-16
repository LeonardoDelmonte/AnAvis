package com.moc.controllers.gestione_sede_avis;

import javax.servlet.http.HttpServletRequest;
import com.moc.dto.RangeDateDto;
import com.moc.models.Prenotazione;
import com.moc.security.UtenteCorrente;
import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * GestioneDateInterface
 */
public interface GestioneSedeAvisInterface {

    @GetMapping(value = "/sede/gestione-date/ottieni-date")
    public ResponseEntity<InterfaceApi> ottieniDate(@AuthenticationPrincipal UtenteCorrente utenteCorrente);

    @PostMapping(value = "/sede/gestione-date/inserisci-data")
    public ResponseEntity<InterfaceApi> inserireDateLibere(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @RequestBody RangeDateDto inserimentoDto);

    @PutMapping(value = "/sede/gestione-date/modifica-data")
    public ResponseEntity<InterfaceApi> modificareData(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @RequestBody Prenotazione prenotazione);

    @DeleteMapping(value = "/sede/gestione-date/elimina-data")
    public ResponseEntity<InterfaceApi> eliminareData(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            HttpServletRequest req);

}