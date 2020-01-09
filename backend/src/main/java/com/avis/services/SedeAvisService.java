package com.avis.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    public void save(SedeAvis sede){
        sedeAvisRepository.save(sede);
    }

    public Set<String> getRegioni(){
        string = new HashSet<>();
        //sedeAvisRepository.findAll().stream().forEach(e->string.add(e.getRegione()));
        string.add("marche");
        return string;
    }
    public Set<String> getProvince(String regione){
        string = new HashSet<>();
        Optional<List<SedeAvis>> listasedi = sedeAvisRepository.findByRegione(regione);
        if (!listasedi.isPresent()){
            return string;
        }
        listasedi.get().forEach(e->string.add(e.getProvincia()));
        /* List<SedeAvis> list = new ArrayList<SedeAvis>();
        list=sedeAvisRepository.findAll();
        list.stream().filter(e->e.getRegione().compareTo(regione)==0);
        list.stream().forEach(e->string.add(e.getProvincia())); */
        return string;
        
    }
    public Set<String> getComuni(String provincia){
        string = new HashSet<>();
        Optional<List<SedeAvis>> listasedi = sedeAvisRepository.findByProvincia(provincia);
        if (!listasedi.isPresent()){
            return string;
        }
        listasedi.get().forEach(e->string.add(e.getComune()));
        /* List<String> string = new ArrayList<String>();
        List<SedeAvis> list = new ArrayList<SedeAvis>();
        list=sedeAvisRepository.findAll();
        list.stream().filter(e->e.getProvincia().compareTo(provincia)==0);
        list.stream().forEach(e->string.add(e.getComune())); */
        return string;
    }

}