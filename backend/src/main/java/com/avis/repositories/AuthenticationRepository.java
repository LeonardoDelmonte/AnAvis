package com.avis.repositories;

import com.avis.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Utente, Long> {

	Utente findByEmail(String email);

}
