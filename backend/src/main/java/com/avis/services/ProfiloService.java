package com.avis.services;

import java.util.Optional;

import com.avis.models.Donatore;
import com.avis.models.Modulo;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.ModuloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfiloService {

    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private DonatoreRepository donatoreRepository;

	public boolean modificaModulo(Modulo newModulo, Long idDonatore) {
        Optional<Donatore> donatore = donatoreRepository.findById(idDonatore);
        Optional<Modulo> oldModulo = moduloRepository.findById(idDonatore);
        if(!donatore.isPresent()){
            return false;
        }
        if(!oldModulo.isPresent()){
            newModulo.setId(idDonatore);
            moduloRepository.save(newModulo);
            donatore.get().setModulo(newModulo);
            donatoreRepository.save(donatore.get());
            return true;
        }else{
            newModulo.setId(idDonatore);
            moduloRepository.save(newModulo);
            return true;
        }
    }
}
