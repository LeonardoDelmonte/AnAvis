package com.avis.services;

import com.avis.models.CentroTrasfusione;

import com.avis.repositories.CentroTrasfusioneRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentroTrasfusioniService {

    @Autowired
    private CentroTrasfusioneRepository centroTrasfusioneRepository;

    public void save(CentroTrasfusione centroTrasfusione){
        centroTrasfusioneRepository.save(centroTrasfusione);
    }

}