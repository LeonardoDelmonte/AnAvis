package com.moc.repositories;


import com.moc.models.Donatore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DonatoreRepository
 */
@Repository
public interface DonatoreRepository extends JpaRepository<Donatore,Long>{

	Donatore findByEmail(String email);

    
}