package com.avis.services;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import com.avis.models.Prenotazione;
import com.avis.repositories.DateRepository;
import org.springframework.stereotype.Service;

@Service
public class DateService implements DateRepository {

    static List<Prenotazione> listDate = new ArrayList<Prenotazione>();

    
    static {
        listDate.add(new Prenotazione(0,"morrovalle",new GregorianCalendar()));
        listDate.add(new Prenotazione(1,"trodica",new GregorianCalendar()));
        listDate.add(new Prenotazione(2,"morrovalle",new GregorianCalendar()));
    }

    public List<Prenotazione> findBySedeAvis(String sede){
        List<Prenotazione> listDateSede = new ArrayList<>();
        for (Prenotazione p : listDate) {
            if(p.getSedeAvis().compareTo(sede)==0 && p.getDonatoreString()==null){
                listDateSede.add(p);
            }
        }
        return listDateSede;
    }

    public boolean prenotaData(int id){
        //controlla se la data esiste ancora
        //aggiorna la data mettendo il donatore
        listDate.get(id).setDonatoreString("giovanni");
        return true;
    }

    public boolean saveNewDate(Prenotazione newDataLibera){
        listDate.add(newDataLibera);      
        return true;
    }

    @Override
    public List<Prenotazione> returnList() {
        return listDate;
    }
    
}