package com.avis.repositories;

import java.util.List;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Emergenza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergenzaRepository extends JpaRepository<Emergenza, Long> {

	List<Emergenza> findByIdCentroTrasfusione(CentroTrasfusione centroTrasfusione);

	@Query(value = "select count(v), v.gruppoSanguigno from Emergenza v group by v.gruppoSanguigno")
	List<?> countGruppo();
}