package com.moc.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;


import com.moc.models.Prenotazione;
import com.moc.models.SedeAvis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PrenotazioneRepository
 */
@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long>{

	List<Prenotazione> findByIdSedeAvisAndDateBetween(
            SedeAvis sede, 
            Timestamp dataIniziale,
            Timestamp dataFinale);

	Prenotazione findByDate(Timestamp date) throws NoSuchElementException;

    
}