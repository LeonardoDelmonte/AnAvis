package com.avis.repositories;

import com.avis.models.Donatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonatoreRepository extends JpaRepository<Donatore, Long>{

}