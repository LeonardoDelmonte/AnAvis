package com.avis.repositories;

import com.avis.models.SedeAvis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeAvisRepository extends JpaRepository<SedeAvis, Long>{

	SedeAvis findByComune(String comune);

}