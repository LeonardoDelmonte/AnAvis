package com.avis.rest_controller;

import javax.servlet.http.HttpServletRequest;

import com.avis.security.JwtTokenUtil;
import com.avis.services.EmergenzaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public boolean insertEmergenza(@RequestBody String gruppo, HttpServletRequest req) {
        Long idCentro = jwtTokenUtil.getIdFromToken(req.getHeader(tokenHeader));
        return emergenzaService.save(gruppo, idCentro);
    }

    @DeleteMapping("/requestEmerg/remove")
    public boolean deleteEmergenza(@RequestBody long emergenza) {
        return emergenzaService.delete(emergenza);
    }

}