package com.avis.rest_controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import com.avis.models.Prenotazione;
import com.avis.security.JwtTokenUtil;
import com.avis.services.PrenotazioniService;
import com.avis.services.SedeAvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins={"*"})
@RestController
public class PrenotazioneController{

    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private SedeAvisService sedeAvisService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.header}")
	private String jwtHeader;

    @PostMapping("/prenotazione")
    public ResponseEntity<List<Prenotazione>> getDateLibere(@RequestBody String comune){
        List<Prenotazione> dateLibere = prenotazioniService.getDateLibere(comune);
        if(dateLibere==null || dateLibere.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Prenotazione>>(dateLibere, HttpStatus.OK);
    }

    @PutMapping("/prenotazione")
    public ResponseEntity<String> prenotaData(@RequestBody Long idDataLibera, HttpServletRequest req){
        Long idDonatore = jwtTokenUtil.getIdFromToken(req.getHeader(jwtHeader));
        if(!prenotazioniService.prenotaData(idDataLibera,idDonatore)){
            return new ResponseEntity<String>("Prenotazione non effettuata", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("Prenotazione effettuata", HttpStatus.OK);
    }

    //vorrei fare soltanto un metodo
    @PostMapping("/prenotazione/getRegioni")
    public ResponseEntity<Set<String>> searchRegioni(){
        return new ResponseEntity<Set<String>>(sedeAvisService.getRegioni(), HttpStatus.OK);
    }   
    @PostMapping("/prenotazione/getProvince")
    public ResponseEntity<Set<String>> searchProvince(@RequestBody String regione){
        return new ResponseEntity<Set<String>>(sedeAvisService.getProvince(regione), HttpStatus.OK);
    }
    @PostMapping("/prenotazione/getComuni")
    public ResponseEntity<Set<String>> searchComuni(@RequestBody String provincia){
        return new ResponseEntity<Set<String>>(sedeAvisService.getComuni(provincia), HttpStatus.OK);
    }

}
