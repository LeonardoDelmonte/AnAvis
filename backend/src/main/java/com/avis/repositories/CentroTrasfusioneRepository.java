package com.avis.repositories;

import com.avis.models.CentroTrasfusione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroTrasfusioneRepository extends JpaRepository<CentroTrasfusione, Long>{

}