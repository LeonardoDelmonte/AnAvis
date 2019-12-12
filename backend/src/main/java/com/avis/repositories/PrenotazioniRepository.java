package com.avis.repositories;


import java.util.Optional;

import com.avis.models.Prenotazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione,Long> {

	Optional<Prenotazione> findByIdSedeAvis(long id);


}