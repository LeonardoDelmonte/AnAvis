package com.moc.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.moc.models.CentroTrasfusione;
import com.moc.models.Emergenza;
import com.moc.repositories.EmergenzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergenzaService implements EmergenzaInterface {

    @Autowired
    private EmergenzaRepository emergenzaRepository;

    @Override
    public Emergenza findById(Long idEmergenza) {
        if(idEmergenza==null)
            throw new NullPointerException("idEmergenza NULL");
        //exception
        return emergenzaRepository.findById(idEmergenza).get();
    }

    @Override
    public void cancellaEmergenza(Emergenza emergenza) {
        if(emergenza==null)
            throw new NullPointerException("emergenza NULL");
        //exception
        emergenzaRepository.delete(emergenza);
    }

    @Override
    public void inserireEmergenza(String gruppo, CentroTrasfusione centro) {
        if(gruppo==null || centro==null)
            throw new NullPointerException("argomento NULL");
        //exception
        emergenzaRepository.save(Emergenza.builder().gruppoSanguigno(gruppo).idCentroTrasfusione(centro)
                .dataEmergenza(new Timestamp(new Date().getTime())).build());
    }

    @Override
    public List<?> contaEmergenze() {
        //exception
        return emergenzaRepository.countGruppo();
    }

}