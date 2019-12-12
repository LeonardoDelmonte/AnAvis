package com.avis.services;

import com.avis.models.Donatore;
import com.avis.repositories.DonatoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonatoreService {

    @Autowired
    private DonatoreRepository donatoreRepository;

    public void save(Donatore donatore){
        donatoreRepository.save(donatore);
    }

}