package com.avis.repositories;

import java.util.List;
import java.util.Optional;

import com.avis.models.SedeAvis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeAvisRepository extends JpaRepository<SedeAvis, Long>{

	SedeAvis findByComune(String comune);

	Optional<List<SedeAvis>> findByRegione(String regione);

	Optional<List<SedeAvis>> findByProvincia(String provincia);



}