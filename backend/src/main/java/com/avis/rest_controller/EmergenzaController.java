package com.avis.rest_controller;

import javax.servlet.http.HttpServletRequest;

import com.avis.security.JwtTokenUtil;
import com.avis.services.EmergenzaService;

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
public class EmergenzaController {

    @Autowired
    private EmergenzaService emergenzaService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.header}")
    private String tokenHeader;

    @PostMapping("/requestEmerg/insert")
    public ResponseEntity<String> insertEmergenza(@RequestBody String gruppo, HttpServletRequest req) {
        Long idCentro = jwtTokenUtil.getIdFromToken(req.getHeader(tokenHeader));
        if (!emergenzaService.save(gruppo, idCentro)) {
            return new ResponseEntity<String>("Emergenza non inviata", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Emergenza inviata correttamente", HttpStatus.OK);
    }

    @DeleteMapping("/requestEmerg/remove")
    public ResponseEntity<String> deleteEmergenza(@RequestBody long emergenza) {
        if (!emergenzaService.delete(emergenza)) {
            new ResponseEntity<String>("Emergenza non cancellata", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Emergenza cancellata", HttpStatus.OK);
    }

}