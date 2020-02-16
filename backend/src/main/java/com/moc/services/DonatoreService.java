package com.moc.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import com.moc.dto.DonatoreProfiloDto;
import com.moc.models.Donatore;
import com.moc.models.Modulo;
import com.moc.models.Prenotazione;
import com.moc.repositories.DonatoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DonatoreService
 */
@Service
public class DonatoreService implements DonatoreInterface {

    @Autowired
    private DonatoreRepository donatoreRepository;

    @Override
    public List<Prenotazione> ottieniPrenotazioni(Donatore donatore) {
        if(donatore==null)
            throw new NullPointerException("donatore NULL");
        return donatore.getListaPrenotazioni();
    }

    @Override
    public Donatore findByEmail(String email) {
        if(email==null)
            throw new NullPointerException("email NULL");
        return donatoreRepository.findByEmail(email);
    }

    @Override
    public void modificaModulo(Donatore donatore, Modulo modulo) {
        if (donatore.getModulo() == null) {
            //se da errore vedi nel modulo se cè il donatore != null
            donatore.setModulo(modulo);
            donatore.setAbilitaDonazione(true);
            donatoreRepository.save(donatore);
        }
    }

    @Override
    public Modulo ottieniModulo(Donatore donatore) {
        //exception donatore null

        if (donatore.getModulo() == null) {
            return new Modulo();
        }

        return donatore.getModulo();
    }

    @Override
    public void isAbilitatoAdonare(Donatore donatore){
        Long date = new Date().getTime();
        Long last = 0L;
        List<Prenotazione> list = donatore.getListaPrenotazioni();
        if (!list.isEmpty())
            last = list.get(list.size() - 1).getDate().getTime();
        if (date - last > 7884008640L) {
            donatore.setAbilitaDonazione(true);
            donatoreRepository.save(donatore);     
        }   
    }

    @Override
    public DonatoreProfiloDto ottieniProfilo(Donatore donatore) {
        ModelMapper mapper = new ModelMapper();
        DonatoreProfiloDto profilo = mapper.map(donatore,DonatoreProfiloDto.class);
        return profilo;
    }

    @Override
    public void modificaProfilo(Donatore donatore, DonatoreProfiloDto profilo) {
        ModelMapper mapper = new ModelMapper();
        mapper.map(profilo, donatore);
        donatoreRepository.save(donatore);
    }


    @Override
    public void isModuloCompilato(Donatore donatore) {
        if(donatore.getModulo()==null)
            throw new NoSuchElementException("non abilitato a donare, modulo non compilato!");
    }

    @Override
    public void setAbilitazioneFalse(Donatore donatore) {
        donatore.setAbilitaDonazione(false);
        donatoreRepository.save(donatore);
    }

    
    
}