package com.moc.services;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.moc.dto.RangeDateDto;
import com.moc.models.Donatore;
import com.moc.models.Prenotazione;
import com.moc.models.SedeAvis;
import com.moc.repositories.PrenotazioneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * PrenotazioneService
 */
@Service
public class PrenotazioneService implements PrenotazioneInterface {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Override
    public Map<String, List<Timestamp>> salvaListaDate(List<Timestamp> list, SedeAvis sedeAvis) {
        if (list.isEmpty() || sedeAvis == null)
            throw new NullPointerException("Argomento passato NULL");

        return inserisciDateLibere(sedeAvis, list);
    }

    private Map<String, List<Timestamp>> inserisciDateLibere(SedeAvis sedeAvis, List<Timestamp> listTimestamp) {
        List<Timestamp> listError = new ArrayList<>();
        List<Timestamp> listOK = new ArrayList<>();
        for (int i = 0; i < listTimestamp.size(); i++) {
            try {
                prenotazioneRepository
                        .save(Prenotazione.builder().date(listTimestamp.get(i)).idSedeAvis(sedeAvis).build());
                listOK.add(listTimestamp.get(i));
            } catch (DataIntegrityViolationException e) {
                listError.add(listTimestamp.get(i));
            }
        }
        Map<String, List<Timestamp>> map = new HashMap<>();
        map.put("ListOK", listOK);
        map.put("ListError", listError);
        return map;
    }

    @Override
    public Prenotazione findById(Long id) {
        if (id == null)
            throw new NullPointerException("id NULL");
        return prenotazioneRepository.findById(id).get();
    }

    @Override
    public Prenotazione prenotaData(Donatore donatore, Prenotazione prenotazione) {
        if (donatore == null || prenotazione == null) {
            throw new NullPointerException("Argomenti non validi");
        }
        if (!donatore.getAbilitaDonazione()) {
            throw new NoSuchElementException("Non sei abilitato a donare, l'ultima donazione è troppo recente!");
        }
        // check se null oppure rendi metodo syhncronized
        prenotazione.setIdDonatore(donatore);
        prenotazioneRepository.save(prenotazione);
        return prenotazione;
    }

    @Override
    public void eliminaData(Prenotazione prenotazione) {
        if (prenotazione == null)
            throw new NullPointerException("Prenotazione NULL");

        if (prenotazione.getIdDonatore() != null) {
            // manda una mail al donatore per comunicare la cancellazione della data
        }

        prenotazioneRepository.delete(prenotazione);
    }

    @Override
    public void cancellaPrenotazione(Prenotazione prenotazione) {
        if (prenotazione.getIdDonatore() == null)
            throw new NoSuchElementException("La data è già libera");
        checkDate(prenotazione);
        prenotazione.setIdDonatore(null);
        prenotazioneRepository.save(prenotazione);
    }

    private void checkDate(Prenotazione prenotazione) {
        if (new Date().getTime() > prenotazione.getDate().getTime()) {
            throw new InvalidParameterException("La donazione è già stata effettuata, non si può eliminare");
        }
    }

    @Override
    public List<Prenotazione> getDateLibere(SedeAvis sedeAvis, RangeDateDto dto) {

        List<Prenotazione> dateLibere = prenotazioneRepository.findByIdSedeAvisAndDateBetween(sedeAvis,
                dto.getDataIniziale(), dto.getDataFinale());

        return dateLibere.stream().filter(e -> e.getIdDonatore() == null).collect(Collectors.toList());
    }

}