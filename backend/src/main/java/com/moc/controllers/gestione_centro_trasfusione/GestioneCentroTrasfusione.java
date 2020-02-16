package com.moc.controllers.gestione_centro_trasfusione;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.moc.models.CentroTrasfusione;
import com.moc.models.Emergenza;
import com.moc.security.UtenteCorrente;
import com.moc.services.CentroTrasfusioneInterface;
import com.moc.services.EmergenzaInterface;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseCustomEntity;
import com.moc.utils.ResponseOK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * GestioneCentroTrasfusione
 */
@RestController
public class GestioneCentroTrasfusione implements GestioneCentroTrasfusioneInterface {

    @Autowired
    private CentroTrasfusioneInterface centroService;
    @Autowired 
    private EmergenzaInterface emergenzaService;

    @Override
    public ResponseEntity<InterfaceApi> ottieniEmergenze(UtenteCorrente utenteCorrente) {
        CentroTrasfusione centro = centroService.findByEmail(utenteCorrente.getEmail());
        List<Emergenza> list = centroService.ottieniEmergenze(centro);
        return new ResponseEntity<>(new ResponseCustomEntity<List<Emergenza>>(list),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> eliminareEmergenza(UtenteCorrente utenteCorrente, HttpServletRequest req) {
        Long idEmergenza = Long.valueOf(req.getHeader("data"));
        Emergenza emergenza = emergenzaService.findById(idEmergenza);
        emergenzaService.cancellaEmergenza(emergenza);
        return new ResponseEntity<>(new ResponseOK("Emergenza eliminata correttamente"),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> inserireEmergenza(UtenteCorrente utenteCorrente, String gruppo) {
        CentroTrasfusione centro = centroService.findByEmail(utenteCorrente.getEmail());
        emergenzaService.inserireEmergenza(gruppo,centro);
        return new ResponseEntity<>(new ResponseOK("Emergenza inserita correttamente"),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<InterfaceApi> contaEmergenze(UtenteCorrente utenteCorrente) {
        List<?> list = emergenzaService.contaEmergenze();
        return new ResponseEntity<>(new ResponseCustomEntity<List<?>>(list),HttpStatus.OK);
    }

    
}