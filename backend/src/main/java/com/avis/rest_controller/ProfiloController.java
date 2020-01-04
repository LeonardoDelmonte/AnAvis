package com.avis.rest_controller;

import javax.servlet.http.HttpServletRequest;

import com.avis.models.Modulo;
import com.avis.security.JwtTokenUtil;
import com.avis.services.ProfiloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins={"*"})
@RestController
public class ProfiloController{

    @Autowired
    private ProfiloService profiloService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.header}")
	private String jwtHeader;

    @PutMapping("/modificaModulo")
    public ResponseEntity<String> modificaModulo(@RequestBody Modulo modulo, HttpServletRequest req){
        Long idDonatore = jwtTokenUtil.getIdFromToken(req.getHeader(jwtHeader));
        if(!profiloService.modificaModulo(modulo,idDonatore)){
            return new ResponseEntity<String>("Modulo non modificato", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("Modulo modificato", HttpStatus.OK); 
    }
}