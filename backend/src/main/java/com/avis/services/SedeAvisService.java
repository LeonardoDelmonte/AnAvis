package com.avis.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.avis.models.SedeAvis;
import com.avis.repositories.SedeAvisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedeAvisService {

    @Autowired
    private SedeAvisRepository sedeAvisRepository;
    private Set<String> string;

    public void save(SedeAvis sede) {
        sedeAvisRepository.save(sede);
    }

    public Set<String> getRegioni() {
        string = new HashSet<>();
        sedeAvisRepository.findAll().stream().forEach(e -> string.add(e.getRegione()));
        return string;
    }

    public Set<String> getProvince(String regione) {
        string = new HashSet<>();
        List<SedeAvis> listasedi = sedeAvisRepository.findByRegione(regione);
        if (listasedi.isEmpty()) {
            return string;
        }
        listasedi.forEach(e -> string.add(e.getProvincia()));
        return string;
    }

    public Set<String> getComuni(String provincia) {
        string = new HashSet<>();
        List<SedeAvis> listasedi = sedeAvisRepository.findByProvincia(provincia);
        if (listasedi.isEmpty()) {
            return string;
        }
        listasedi.forEach(e -> string.add(e.getComune()));
        return string;
    }

}