package com.avis.repositories;

import com.avis.models.Ente;
import com.avis.models.Utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Ente, Long>{

}