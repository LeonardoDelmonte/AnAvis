package com.moc.services;

import com.moc.models.Donatore;
import com.moc.models.Modulo;
import com.moc.repositories.ModuloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ModuloService
 */
@Service
public class ModuloService implements ModuloInterface{

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public Modulo ottieniModulo(Donatore donatore) {
        if(donatore.getModulo()==null){
            return new Modulo();
        }
        return donatore.getModulo();        
    }

    @Override
    public Modulo modificaModulo(Donatore donatore,Modulo modulo) {
        if(donatore == null || modulo==null)
            throw new NullPointerException("argomenti null");

        return moduloRepository.save(modulo);
        
    }
    
}