package com.avis.services;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.avis.models.SedeAvis;
import org.springframework.stereotype.Service;

@Service
public class SedeService {
    static List<SedeAvis> listSede = new ArrayList<SedeAvis>();

    static {
        listSede.add(new SedeAvis(0, "marche", "macerata", "morrovalle"));
        listSede.add(new SedeAvis(1, "marche", "macerata", "montecosaro"));
        listSede.add(new SedeAvis(2, "marche", "ancona", "osimo"));
        listSede.add(new SedeAvis(3, "marche", "ancona", "corinaldo"));
        listSede.add(new SedeAvis(4, "marche", "ascoli", "sanbenedetto"));
        listSede.add(new SedeAvis(5, "umbria", "ascoli", "centobuchi"));
        listSede.add(new SedeAvis(6, "umbria", "perugia", "spello"));
        listSede.add(new SedeAvis(7, "lazio", "roma", "ostia"));
        listSede.add(new SedeAvis(8, "toscana", "firenze", "fiesole"));

    }

    public Set<String> retrieveAllRegions() {
        Set<String> regioni = new HashSet<>();
        for (SedeAvis p : listSede) {
            regioni.add(p.getRegione());
        }

        return regioni;

    }

    public Set<String> retrieveAllProvincie(String regione) {
        Set<String> provincie = new HashSet<>();
        for (SedeAvis p : listSede) {
            if(p.getRegione().compareTo(regione)==0){
                provincie.add(p.getProvincia());
            }
        }

        return provincie;

    }

    public Set<String> retrieveAllComuni(String provincia) {
        Set<String> comuni = new HashSet<>();
        for (SedeAvis p : listSede) {
            if(p.getProvincia().compareTo(provincia)==0){
                comuni.add(p.getComune());
            }
        }

        return comuni;

    }
}