package com.avis.repositories;
import com.avis.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Utente, Long> {

	Utente findByEmail(String email);

}
