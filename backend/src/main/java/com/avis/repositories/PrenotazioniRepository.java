package com.avis.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.avis.models.Prenotazione;
import com.avis.models.SedeAvis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione,Long> {

	Optional<List<Prenotazione>> findByIdSedeAvis(SedeAvis sede);

	Optional<List<Prenotazione>> findByIdSedeAvisAndDateBetween(SedeAvis sede, Timestamp dataIniziale,
			Timestamp dataFinale);

}