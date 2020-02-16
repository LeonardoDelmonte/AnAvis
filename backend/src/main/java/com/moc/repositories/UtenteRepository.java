package com.moc.repositories;

import com.moc.models.Utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UtenteRepository
 */

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long>{

	Utente findByEmail(String email);

    
}