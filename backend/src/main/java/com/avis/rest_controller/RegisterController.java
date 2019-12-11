package com.avis.rest_controller;
import com.avis.models.Donatore;
import com.avis.services.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"*"})
public class RegisterController {

    @Autowired
    private UtenteService utenteService;

    /* TO DO */
    @RequestMapping(value = "public/register", method = RequestMethod.POST)
    public boolean createUser(@RequestBody Donatore donatore){ 
        utenteService.save(donatore);       
        return true;
    }
}