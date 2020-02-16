package com.moc.repositories;

import com.moc.models.Modulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ModuloRepository
 */
@Repository
public interface ModuloRepository extends JpaRepository<Modulo,Long>{

    
}