package com.moc.controllers.gestione_profilo;

import com.moc.dto.CentroTrasfusioneProfiloDto;
import com.moc.dto.DonatoreProfiloDto;
import com.moc.dto.SedeAvisProfiloDto;
import com.moc.security.UtenteCorrente;
import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * GestioneProfiloInterface
 */
public interface GestioneProfiloInterface {

    @PutMapping(value = "/donatore/gestione-profilo/modifica-profilo")
    public ResponseEntity<InterfaceApi> modificareProfiloDonatore(
            @AuthenticationPrincipal UtenteCorrente utenteCorrente, @RequestBody DonatoreProfiloDto profilo);

    @PutMapping(value = "/sede/gestione-profilo/modifica-profilo")
    public ResponseEntity<InterfaceApi> modificareProfiloSedeAvis(
            @AuthenticationPrincipal UtenteCorrente utenteCorrente, @RequestBody SedeAvisProfiloDto profilo);

    @PutMapping(value = "/centro/gestione-profilo/modifica-profilo")
    public ResponseEntity<InterfaceApi> modificareProfiloCentroTrasfusione(
            @AuthenticationPrincipal UtenteCorrente utenteCorrente, @RequestBody CentroTrasfusioneProfiloDto profilo);

    @GetMapping(value = "/donatore/gestione-profilo/ottieni-profilo")
    public ResponseEntity<InterfaceApi> ottenereProfiloDonatore(@AuthenticationPrincipal UtenteCorrente utenteCorrente);

    @GetMapping(value = "/sede/gestione-profilo/ottieni-profilo")
    public ResponseEntity<InterfaceApi> ottenereProfiloSedeAvis(@AuthenticationPrincipal UtenteCorrente utenteCorrente);

    @GetMapping(value = "/centro/gestione-profilo/ottieni-profilo")
    public ResponseEntity<InterfaceApi> ottenereProfiloCentroTrasfusione(
            @AuthenticationPrincipal UtenteCorrente utenteCorrente);

}