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
    public List<Prenotazione> getDateLibere(@RequestBody String comune){
        List<Prenotazione> dateLibere = prenotazioniService.getDateLibere(comune);
        if(dateLibere==null || dateLibere.isEmpty()){
            return null;
        }
        return dateLibere;
    }

    @PutMapping("/prenotazione")
    public boolean prenotaData(@RequestBody Long idDataLibera, HttpServletRequest req){
        Long idDonatore = jwtTokenUtil.getIdFromToken(req.getHeader(jwtHeader));
        prenotazioniService.prenotaData(idDataLibera,idDonatore);
        return true;
    }


    //vorrei fare soltanto un metodo
    @PostMapping("/prenotazione/getRegioni")
    public Set<String> searchRegioni(){
        return sedeAvisService.getRegioni();
    }   
    @PostMapping("/prenotazione/getProvince")
    public Set<String> searchProvince(@RequestBody String regione){
        return sedeAvisService.getProvince(regione);
    }
    @PostMapping("/prenotazione/getComuni")
    public Set<String> searchComuni(@RequestBody String provincia){
        return sedeAvisService.getComuni(provincia);
    }

}
