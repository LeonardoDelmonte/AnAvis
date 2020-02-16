package com.moc.controllers.gestione_donatore;

import javax.servlet.http.HttpServletRequest;
import com.moc.security.UtenteCorrente;
import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * GestioneDonatoreInterface
 */
public interface GestioneDonatoreInterface {

    @GetMapping(value = "/donatore/gestione-date/ottieni-date")
    public ResponseEntity<InterfaceApi> ottieniDate(@AuthenticationPrincipal UtenteCorrente utenteCorrente);

    @DeleteMapping(value = "/donatore/gestione-date/eliminare-data")
    public ResponseEntity<InterfaceApi> eliminareData(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            HttpServletRequest req);

}