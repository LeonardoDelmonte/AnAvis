package com.avis.services;

import com.avis.models.Emergenza;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.EmergenzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergenzaService {

    @Autowired
    private EmergenzaRepository emergenzaRepository;
    @Autowired
    private CentroTrasfusioneRepository centroRepository;

    public boolean delete(long idEmergenza) {
        emergenzaRepository.delete(emergenzaRepository.findById(idEmergenza).get());
        return true;
    }

    public boolean save(String gruppo, Long idCentro) {
        emergenzaRepository.save(new Emergenza(centroRepository.findById(idCentro).get(), gruppo));
        return true;
    }

}