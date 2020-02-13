package com.avis.repositories;

import java.sql.Timestamp;
import java.util.List;

import com.avis.models.Donatore;
import com.avis.models.Prenotazione;
import com.avis.models.SedeAvis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, Long> {

	List<Prenotazione> findByIdSedeAvisAndDateBetween(SedeAvis sede, Timestamp dataIniziale, Timestamp dataFinale);

	List<Prenotazione> findByIdDonatore(Donatore donatore);

	List<Prenotazione> findByIdSedeAvisAndDate(SedeAvis sedeAvis, Timestamp data1);

	List<Prenotazione> findByIdSedeAvisAndDateAfter(SedeAvis sedeAvis, Timestamp timestamp);

	@Query(value = "select count(v), monthname(v.date) from Prenotazione v where ( v.idDonatore is not null ) group by monthname(v.date) order by month(v.date)")
	List<?> countPrenotazioni();
}
