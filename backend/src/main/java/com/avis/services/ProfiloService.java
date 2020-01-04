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

    // ho fatto la prova con riccà, il set modulo direttamente non si può fare,tocca dare 100 set :(
	public boolean modificaModulo(Modulo modulo, Long idDonatore) {
        Optional<Donatore> donatore = donatoreRepository.findById(idDonatore);
        if (!donatore.isPresent()){
            return false;
        }
        donatore.get().getModulo().setFumatore(modulo.getFumatore());
        donatore.get().getModulo().setGruppoSanguigno(modulo.getGruppoSanguigno());
        moduloRepository.save(donatore.get().getModulo());
		return true;
	}

}
