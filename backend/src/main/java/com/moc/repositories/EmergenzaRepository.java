package com.moc.repositories;

import java.util.List;

import com.moc.models.Emergenza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * EmergenzaRepository
 */
@Repository
public interface EmergenzaRepository extends JpaRepository<Emergenza,Long>{

    @Query(value = "select count(v), v.gruppoSanguigno from Emergenza v group by v.gruppoSanguigno")
	List<?> countGruppo();
}