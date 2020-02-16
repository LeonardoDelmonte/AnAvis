package com.moc.controllers.gestione_modulo;

import com.moc.dto.ModuloPerTerzi;
import com.moc.models.Donatore;
import com.moc.models.Modulo;
import com.moc.security.UtenteCorrente;
import com.moc.services.DonatoreInterface;
import com.moc.services.ModuloInterface;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseCustomEntity;
import com.moc.utils.ResponseOK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * GestioneModulo
 */
@RestController
public class GestioneModulo implements GestioneModuloInterface {

    @Autowired
    private DonatoreInterface donatoreService;
    @Autowired
    private ModuloInterface moduloService;

    @Override
    public ResponseEntity<InterfaceApi> ottieniModuloDonatore(UtenteCorrente utenteCorrente, String email) {
        Modulo modulo = getModulo(email);
        return new ResponseEntity<>(new ResponseCustomEntity<Modulo>(modulo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> ottieniModulo(UtenteCorrente utenteCorrente) {
        Modulo modulo = getModulo(utenteCorrente.getEmail());
        return new ResponseEntity<>(new ResponseCustomEntity<Modulo>(modulo), HttpStatus.OK);
    }

    private Modulo getModulo(String email) {
        Donatore donatore = donatoreService.findByEmail(email);
        return donatoreService.ottieniModulo(donatore);
    }

    @Override
    public ResponseEntity<InterfaceApi> modificaModuloPerTerzi(UtenteCorrente utenteCorrente,
            ModuloPerTerzi moduloPerTerziDto) {
        modificaModulo(moduloPerTerziDto.getEmail(), moduloPerTerziDto.getModulo());
        return new ResponseEntity<>(new ResponseOK("Modulo modificato con successo"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> modificareModulo(UtenteCorrente utenteCorrente, Modulo modulo) {
        modificaModulo(utenteCorrente.getEmail(), modulo);
        return new ResponseEntity<>(new ResponseOK("Modulo modificato con successo"), HttpStatus.OK);
    }

    private void modificaModulo(String email, Modulo modulo) {
        Donatore donatore = donatoreService.findByEmail(email);
        Modulo moduloSaved = moduloService.modificaModulo(donatore, modulo);
        donatoreService.modificaModulo(donatore, moduloSaved);
    }

}