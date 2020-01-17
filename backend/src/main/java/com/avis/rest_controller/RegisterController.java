package com.avis.rest_controller;

import com.avis.dto.CredenzialiDto;
import com.avis.services.AuthenticationService;
import com.avis.utils.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = "public/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody CredenzialiDto utente) {
        if (!authService.save(utente)) {
            return new ResponseEntity<>(new ApiResponse("Utente non registrato"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse("Utente registrato con successo"), HttpStatus.CREATED);
    }

}