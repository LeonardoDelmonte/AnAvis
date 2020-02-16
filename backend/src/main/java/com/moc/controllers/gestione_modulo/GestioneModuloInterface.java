package com.moc.controllers.gestione_modulo;

import com.moc.dto.ModuloPerTerzi;
import com.moc.models.Modulo;
import com.moc.security.UtenteCorrente;
import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * GestioneModuloInterface
 */
public interface GestioneModuloInterface {

    @GetMapping(value = "/sede/ottieni-modulo-donatore/{email}")
    public ResponseEntity<InterfaceApi> ottieniModuloDonatore(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @PathVariable String email);

    @PutMapping(value = "/sede/modifica-modulo-donatore")
    public ResponseEntity<InterfaceApi> modificaModuloPerTerzi(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @RequestBody ModuloPerTerzi moduloPerTerziDto);

    @GetMapping(value = "/donatore/gestione-modulo/ottieni-modulo")
    public ResponseEntity<InterfaceApi> ottieniModulo(@AuthenticationPrincipal UtenteCorrente utenteCorrente);

    @PutMapping(value = "/donatore/gestione-modulo/modifica-modulo")
    public ResponseEntity<InterfaceApi> modificareModulo(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            @RequestBody Modulo modulo);

}