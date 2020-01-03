package com.avis.rest_controller;

import javax.servlet.http.HttpServletRequest;
import com.avis.dto.DateDto;
import com.avis.security.JwtTokenUtil;
import com.avis.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
public class DateController {

    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.header}")
    private String tokenHeader;

    @PostMapping("/handlerDate/insert")
    public ResponseEntity<String> inserisciDate(@RequestBody DateDto dateLibere, HttpServletRequest req) {
        Long idSede = jwtTokenUtil.getIdFromToken(req.getHeader(tokenHeader));     
        if(!prenotazioniService.save(dateLibere,idSede)){
            return new ResponseEntity<String>("Data non inserita", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Data inserita correttamente", HttpStatus.OK);
    }

    @DeleteMapping("/handlerDate/remove")
    public ResponseEntity<String> deleteDate(@RequestBody long prenotazione) {
        if(!prenotazioniService.delete(prenotazione)){
            return new ResponseEntity<String>("Data non cancellata", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Data rimossa correttamente", HttpStatus.OK);
    }


}
