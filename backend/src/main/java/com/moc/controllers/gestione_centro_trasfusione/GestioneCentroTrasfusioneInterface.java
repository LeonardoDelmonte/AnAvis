package com.moc.controllers.gestione_centro_trasfusione;

import javax.servlet.http.HttpServletRequest;

import com.moc.security.UtenteCorrente;
import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GestioneCentroTrasfusioneInterface
 */
public interface GestioneCentroTrasfusioneInterface {

    @GetMapping(value = "/centro/ottieni-emergenze")
    public ResponseEntity<InterfaceApi> ottieniEmergenze(@AuthenticationPrincipal UtenteCorrente utenteCorrente);

    @DeleteMapping(value = "/centro/elimina-emergenze")
    public ResponseEntity<InterfaceApi> eliminareEmergenza(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            HttpServletRequest req);

    @PostMapping(value = "/centro/inserisci-emergenze")
    public ResponseEntity<InterfaceApi> inserireEmergenza(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @RequestBody String gruppo);

    @GetMapping(value = "/vedi-richieste/conta-emergenze")
    public @ResponseBody ResponseEntity<InterfaceApi> contaEmergenze(
            @AuthenticationPrincipal UtenteCorrente utenteCorrente);

}