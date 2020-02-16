package com.moc.controllers.gestione_sede_avis;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.moc.components.ControllaDate;
import com.moc.dto.RangeDateDto;
import com.moc.models.Prenotazione;
import com.moc.models.SedeAvis;
import com.moc.security.UtenteCorrente;
import com.moc.services.PrenotazioneInterface;
import com.moc.services.SedeAvisInterface;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseCustomEntity;
import com.moc.utils.ResponseOK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * GestioneDateSedeAvis può entrare qui solo la sedeAvis
 * 
 * //TO DO// implementa modifica ed elimina
 */
@RestController
public class GestioneSedeAvis implements GestioneSedeAvisInterface {

    @Autowired
    private SedeAvisInterface sedeAvisService;
    @Autowired
    private PrenotazioneInterface prenotazioneService;
    @Autowired
    private ControllaDate controllaDate;

    @Override
    public ResponseEntity<InterfaceApi> ottieniDate(@AuthenticationPrincipal UtenteCorrente utenteCorrente) {

        SedeAvis sedeAvis = sedeAvisService.findByEmail(utenteCorrente.getEmail());

        Map<String, List<Prenotazione>> map = sedeAvisService.ottieni(sedeAvis);

        return new ResponseEntity<>(new ResponseCustomEntity<Map<String, List<Prenotazione>>>(map), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> inserireDateLibere(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            RangeDateDto rangeDateDto) {

        SedeAvis sedeAvis = sedeAvisService.findByEmail(utenteCorrente.getEmail());

        List<Timestamp> list = controllaDate.formattaRangeDate(rangeDateDto);

        Map<String, List<Timestamp>> map = prenotazioneService.salvaListaDate(list, sedeAvis);

        return new ResponseEntity<>(new ResponseCustomEntity<Map<String, List<Timestamp>>>(map), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterfaceApi> modificareData(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            Prenotazione prenotazione) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<InterfaceApi> eliminareData(@AuthenticationPrincipal UtenteCorrente utenteCorrente,
            HttpServletRequest req) {
        Long idPrenotazione = Long.valueOf(req.getHeader("data"));
        Prenotazione prenotazione = prenotazioneService.findById(idPrenotazione);
        prenotazioneService.eliminaData(prenotazione);
        return new ResponseEntity<>(new ResponseOK("Data cancellata con successo"), HttpStatus.OK);
    }

}