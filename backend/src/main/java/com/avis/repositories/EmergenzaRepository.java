package com.avis.repositories;

import java.util.List;
import java.util.Optional;

import com.avis.models.Emergenza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergenzaRepository extends JpaRepository<Emergenza, Long> {

	Optional<List<Emergenza>> findByIdCentroTrasfusione(Long id);

}