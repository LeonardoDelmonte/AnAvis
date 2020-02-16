package com.moc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.moc.dto.SedeAvisProfiloDto;
import com.moc.models.Prenotazione;
import com.moc.models.SedeAvis;
import com.moc.repositories.SedeAvisRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SedeAvisService
 */
@Service
public class SedeAvisService implements SedeAvisInterface {

    @Autowired
    private SedeAvisRepository sedeAvisRepository;

    @Override
    public Map<String, List<Prenotazione>> ottieni(SedeAvis sedeAvis) {
        if (sedeAvis == null)
            throw new NullPointerException("sedeAvis NULL");

        return ottieniLibereEprenotate(sedeAvis);

    }

    private Map<String, List<Prenotazione>> ottieniLibereEprenotate(SedeAvis sedeAvis) {
        List<Prenotazione> listaLibere = sedeAvis.getListaPrenotazioni().stream().filter(e -> e.getIdDonatore() == null)
                .collect(Collectors.toList());

        List<Prenotazione> listaPrenotate = sedeAvis.getListaPrenotazioni().stream()
                .filter(e -> e.getIdDonatore() != null).collect(Collectors.toList());

        Map<String, List<Prenotazione>> map = new HashMap<>();
        map.put("listaLibere", listaLibere);
        map.put("listaPrenotate", listaPrenotate);

        return map;
    }

    @Override
    public SedeAvis findByEmail(String email) {
        if (email == null)
            throw new NullPointerException("email NULL");
        return sedeAvisRepository.findByEmail(email);
    }

    @Override
    public SedeAvis findByComune(String comune) {
        return sedeAvisRepository.findByComune(comune);
    }

    @Override
    public void modificaProfilo(SedeAvis sedeAvis, SedeAvisProfiloDto profilo) {
        ModelMapper mapper = new ModelMapper();
        mapper.map(profilo, sedeAvis);
        sedeAvisRepository.save(sedeAvis);
    }

    @Override
    public SedeAvisProfiloDto ottieniProfilo(SedeAvis sedeAvis) {
        ModelMapper mapper = new ModelMapper();
        SedeAvisProfiloDto profilo = mapper.map(sedeAvis, SedeAvisProfiloDto.class);
        return profilo;
    }

    @Override
    public Set<String> getAllRegioni() {
        Set<String> string = new HashSet<>();
        sedeAvisRepository.findAll().stream().forEach(e -> string.add(e.getRegione()));
        return string;
    }

    @Override
    public Set<String> getAllProvince(String regione) {
        Set<String> string = new HashSet<>();
        List<SedeAvis> listasedi = sedeAvisRepository.findByRegione(regione);
        if (listasedi.isEmpty())
            return string;
        listasedi.forEach(e -> string.add(e.getProvincia()));
        return string;
    }

    @Override
    public Set<String> getComune(String provincia) {
        Set<String> string = new HashSet<>();
        List<SedeAvis> listasedi = sedeAvisRepository.findByProvincia(provincia);
        if (listasedi.isEmpty())
            return string;
        listasedi.forEach(e -> string.add(e.getComune()));
        return string;
    }

    @Override
    public List<String> getAllComuni() {
        List<String> comuni = new ArrayList<>();
        for(SedeAvis sede : sedeAvisRepository.findAll()){
            comuni.add(sede.getComune());
        }
        return comuni;
    }

}