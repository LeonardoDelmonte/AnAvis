package com.moc.repositories;

import com.moc.models.CentroTrasfusione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CentroTrasfusione
 */
@Repository
public interface CentroTrasfusioneRepository extends JpaRepository<CentroTrasfusione,Long>{

	CentroTrasfusione findByEmail(String email);
    
}