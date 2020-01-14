package com.avis.services;

import java.util.Optional;

import com.avis.dto.CredenzialiDto;
import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.Modulo;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.ModuloRepository;
import com.avis.repositories.SedeAvisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfiloService {

    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private DonatoreRepository donatoreRepository;
    @Autowired
    private SedeAvisRepository sedeAvisRepository;
    @Autowired
    private CentroTrasfusioneRepository centroRepository;

    public boolean modificaModulo(Modulo newModulo, Long idDonatore) {
        Optional<Donatore> donatore = donatoreRepository.findById(idDonatore);
        if (!donatore.isPresent()) {
            return false;
        }
        Optional<Modulo> oldModulo = moduloRepository.findById(idDonatore);
        if (!oldModulo.isPresent()) {
            newModulo.setId(idDonatore);
            moduloRepository.save(newModulo);
            donatore.get().setModulo(newModulo);
            donatore.get().setAbilitazioneDonazione((byte)1);
            donatoreRepository.save(donatore.get());
            return true;
        } else {
            moduloRepository.save(newModulo);
            return true;
        }
    }


    public Utente showInfo(Utente utente) {
        switch (utente.getRuolo()) {
        case "donatore":
            return donatoreRepository.findById(utente.getId()).get();
        case "sedeAvis":
            return sedeAvisRepository.findById(utente.getId()).get();
        case "centroTrasfusione":
            return centroRepository.findById(utente.getId()).get();
        default:
            return null;
        }
    }
    
    
    /* public boolean modificaCredenziali(Donatore donatore,Long id) {
        if(donatore==null)
            return false;
        if(id==donatore.getId()){
            donatoreRepository.save(donatore);
            return true;           
        }else{
            return false;
        }
    }
    public boolean modificaCredenziali(SedeAvis sede,Long id) {
        if(sede==null)
            return false;
        if(id==sede.getId()){
            sedeAvisRepository.save(sede);
            return true;
        }else{
            return false;
        }
    }
    public boolean modificaCredenziali(CentroTrasfusione centro,Long id) {
        if(centro==null)
            return false;
        if(id==centro.getId()){
            centroRepository.save(centro);
            return true;
        }else{
            return false;
        }
    } */


	public Boolean modificaCredenziali(CredenzialiDto credenziali, long id, String ruolo) {
        switch(ruolo){
            case "donatore":
                Donatore don = credenziali.getDonatore();
                if(don!=null && id==don.getId()){
                    donatoreRepository.save(don);
                    return true;
                }
                break;
            case "sedeAvis":
                SedeAvis sede = credenziali.getSedeAvis();
                if(sede!=null && id==sede.getId()){
                    sedeAvisRepository.save(sede);
                    return true;
                }
                break;
            case "centroTrasfusione":
                CentroTrasfusione centro = credenziali.getCentroTrasfusione();
                if(centro!=null && id==centro.getId()){
                    centroRepository.save(centro);
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
		
	}      
}
