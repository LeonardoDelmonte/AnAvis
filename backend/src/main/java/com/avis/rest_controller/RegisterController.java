package com.avis.rest_controller;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.SedeAvis;
import com.avis.services.CentroTrasfusioniService;
import com.avis.services.DonatoreService;
import com.avis.services.SedeAvisService;

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
    private SedeAvisService sedeAvisService;
    @Autowired
    private DonatoreService donatoreService;
    @Autowired
    private CentroTrasfusioniService centroTrasfusioniService;

    @RequestMapping(value = "public/register/centroTrasfusioni", method = RequestMethod.POST)
    public boolean createCentro(@RequestBody CentroTrasfusione centroTrasfusioni) {
        centroTrasfusioniService.save(centroTrasfusioni);
        return true;
    }

    @RequestMapping(value = "public/register/sedeAvis", method = RequestMethod.POST)
    public boolean createSede(@RequestBody SedeAvis sede) {
        sedeAvisService.save(sede);
        return true;
    }

    @RequestMapping(value = "public/register/donatore", method = RequestMethod.POST)
    public boolean createDonatore(@RequestBody Donatore donatore) {
        donatoreService.save(donatore);
        return true;
    }

}