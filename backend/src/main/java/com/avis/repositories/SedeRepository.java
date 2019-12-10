package com.avis.repositories;

import com.avis.models.Ente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Ente, Long>{

}