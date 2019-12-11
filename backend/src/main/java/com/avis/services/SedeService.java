package com.avis.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.avis.models.Ente;
import com.avis.repositories.SedeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    public Set<String> retrieveAllRegions(){
        List<Ente> listaEnti = new ArrayList<>();
        Set<String> regioni = new HashSet<>();
        listaEnti = sedeRepository.findAll();
        listaEnti.stream().forEach(e->regioni.add(e.getRegione()));
        return regioni;
    }

    public Set<String> retrieveAllProvincie(String regione) {
        List<Ente> listaEnti = new ArrayList<>();
        Set<String> provincie = new HashSet<>();
        listaEnti.stream().filter(e -> e.getRegione().compareTo(regione)==0);
        listaEnti.forEach(e->provincie.add(e.getProvincia()));
        return provincie;
    }

    public Set<String> retrieveAllComuni(String provincia) {
        List<Ente> listaEnti = new ArrayList<>();
        Set<String> comuni = new HashSet<>();
        listaEnti.stream().filter(e -> e.getProvincia().compareTo(provincia)==0);
        listaEnti.forEach(e->comuni.add(e.getComuni()));
        return comuni;
    }
    
}