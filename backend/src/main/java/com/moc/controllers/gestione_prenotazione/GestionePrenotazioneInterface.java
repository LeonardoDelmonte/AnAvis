package com.moc.controllers.gestione_prenotazione;

import com.moc.dto.DtoCercaDateLibere;
import com.moc.dto.PrenotazionePerTerziDto;
import com.moc.security.UtenteCorrente;
import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * GestionePrenotazioneInterface
 */
public interface GestionePrenotazioneInterface {

    @PostMapping(value = "/sede/prenota-per-donatore")
    public ResponseEntity<InterfaceApi> prenotaPerTerzi(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @RequestBody PrenotazionePerTerziDto prenotazionePerTerziDto);

    @PostMapping(value = "/donatore/prenota-data")
    public ResponseEntity<InterfaceApi> prenotaData(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @RequestBody Long idPrenotazione);

    @PostMapping(value = "/prenotazione/ottieni-date-libere")
    public ResponseEntity<InterfaceApi> ottieniDateLibere(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @RequestBody DtoCercaDateLibere dto);

    @GetMapping(value = "/prenotazione/ottieni-regioni")
    public ResponseEntity<InterfaceApi> ottieniRegioni(@AuthenticationPrincipal UtenteCorrente utenteCorrente);

    @GetMapping(value = "/prenotazione/ottieni-provincie/{string}")
    public ResponseEntity<InterfaceApi> ottieniProvincie(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @PathVariable String string);

    @GetMapping(value = "/prenotazione/ottieni-comuni/{string}")
    public ResponseEntity<InterfaceApi> ottieniComuni(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @PathVariable String string);

}