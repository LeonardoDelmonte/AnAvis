package com.avis.rest_controller;

import javax.servlet.http.HttpServletRequest;
import com.avis.dto.DateDto;
import com.avis.security.JwtTokenUtil;
import com.avis.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public boolean insertDate(@RequestBody DateDto dateLibere, HttpServletRequest req) {
        Long idSede = jwtTokenUtil.getIdFromToken(req.getHeader(tokenHeader));
        return prenotazioniService.save(dateLibere, idSede);
    }

    @DeleteMapping("/handlerDate/remove")
    public boolean deleteDate(@RequestBody long prenotazione) {
        return prenotazioniService.delete(prenotazione);
    }

}
