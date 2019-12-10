package com.avis.services;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.avis.models.Ente;
import org.springframework.stereotype.Service;

@Service
public class SedeService {
    static List<Ente> listSede = new ArrayList<Ente>();

    static {
        listSede.add(new Ente(0, "marche", "macerata", "morrovalle"));
        listSede.add(new Ente(1, "marche", "macerata", "montecosaro"));
        listSede.add(new Ente(2, "marche", "ancona", "osimo"));
        listSede.add(new Ente(3, "marche", "ancona", "corinaldo"));
        listSede.add(new Ente(4, "marche", "ascoli", "sanbenedetto"));
        listSede.add(new Ente(5, "umbria", "ascoli", "centobuchi"));
        listSede.add(new Ente(6, "umbria", "perugia", "spello"));
        listSede.add(new Ente(7, "lazio", "roma", "ostia"));
        listSede.add(new Ente(8, "toscana", "firenze", "fiesole"));

    }

    public Set<String> retrieveAllRegions() {
        Set<String> regioni = new HashSet<>();
        for (Ente p : listSede) {
            regioni.add(p.getRegione());
        }

        return regioni;

    }

    public Set<String> retrieveAllProvincie(String regione) {
        Set<String> provincie = new HashSet<>();
        for (Ente p : listSede) {
            if(p.getRegione().compareTo(regione)==0){
                provincie.add(p.getProvincia());
            }
        }

        return provincie;

    }

    public Set<String> retrieveAllComuni(String provincia) {
        Set<String> comuni = new HashSet<>();
        for (Ente p : listSede) {
            if(p.getProvincia().compareTo(provincia)==0){
                comuni.add(p.getComune());
            }
        }

        return comuni;

    }
}