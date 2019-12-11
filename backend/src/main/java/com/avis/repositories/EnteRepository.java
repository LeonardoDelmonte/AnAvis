package com.avis.repositories;

import com.avis.models.Ente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnteRepository extends JpaRepository<Ente, Long>{

}