package com.avis.repositories;

import java.util.List;
import com.avis.models.Prenotazione;

//questo avrà le CRUDQ per la tabella prenotazioni

public interface DateRepository {

    public List<Prenotazione> returnList();

}