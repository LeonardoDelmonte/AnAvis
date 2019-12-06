package com.avis.services;
//questo lavorerà con DataRepositories e avrà delle CRUD customizzate da noi

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import com.avis.models.Prenotazione;
import com.avis.repositories.DateRepository;
import org.springframework.stereotype.Service;

@Service
public class DateService implements DateRepository {

    List<Prenotazione> listDate = new ArrayList<Prenotazione>();

    @Override
    public List<Prenotazione> returnList() {
        listDate.add(new Prenotazione("morrovalle",new GregorianCalendar()));
        listDate.add(new Prenotazione("trodica",new GregorianCalendar()));
        return listDate;
    }
    
}