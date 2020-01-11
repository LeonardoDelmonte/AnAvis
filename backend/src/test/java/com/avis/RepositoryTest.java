package com.avis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.Emergenza;
import com.avis.models.Modulo;
import com.avis.models.Prenotazione;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.repositories.AuthenticationRepository;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.EmergenzaRepository;
import com.avis.repositories.ModuloRepository;
import com.avis.repositories.PrenotazioniRepository;
import com.avis.repositories.SedeAvisRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RepositoryTest {

    @Autowired
    private AuthenticationRepository utenteRep;
    @Autowired
    private DonatoreRepository donatoreRep;
    @Autowired
    private SedeAvisRepository sedeAvisRep;
    @Autowired
    private CentroTrasfusioneRepository centroRep;
    @Autowired
    private PrenotazioniRepository prenotazioniRep;
    @Autowired
    private ModuloRepository moduloRep;
    @Autowired
    private EmergenzaRepository emergenzaRep;
    
    @Test
    public void donatoreTest(){
        Donatore don= donatoreRep.save(new Donatore("ric","ric","donatore"));
        Utente utente = utenteRep.findByEmail("ric");
        assertTrue(utente!=null);
        assertEquals(don.getId(), utente.getId());
        assertEquals(don.getEmail(), utente.getEmail());
        assertEquals(don.getRuolo(), utente.getRuolo());
    }

    @Test
    public void sedeAvisTest(){
        SedeAvis sede = sedeAvisRep.save(new SedeAvis("leo","leo","sedeAvis"));
        Utente utente = utenteRep.findByEmail("leo");
        assertTrue(utente!=null);
        assertEquals(sede.getId(), utente.getId());
        assertEquals(sede.getPassword(), utente.getPassword());
        assertEquals(sede.getRuolo(), utente.getRuolo());
    }

    @Test
    public void CentroTest(){
        CentroTrasfusione centro = centroRep.save(new CentroTrasfusione("lore","lore","centro"));
        Utente utente = utenteRep.findByEmail("lore");
        assertTrue(utente!=null);
        assertEquals(centro.getId(), utente.getId());
        assertEquals(centro.getEmail(), utente.getEmail());
        assertEquals(centro.getRuolo(), utente.getRuolo());
    }

    @Test
    public void PrenotazioneTest(){
        Timestamp time = Timestamp.valueOf("2020-08-14 11:00:00");
        SedeAvis sede =sedeAvisRep.save(new SedeAvis("pippo","pippo","sedeAvis"));
        Prenotazione prenotazione = prenotazioniRep.save(new Prenotazione(sedeAvisRep.getOne(sede.getId()), time));
        Prenotazione found = prenotazioniRep.getOne(1L);
        assertTrue(prenotazione!=null);
        assertEquals(found.getIdPrenotazione(), prenotazione.getIdPrenotazione());
        assertEquals(sede.getId(), prenotazione.getIdSedeAvis().getId());
        assertEquals(time.toString(), prenotazione.getDate().toString());
    }

    @Test
    public void ModuloTest(){
        Modulo modulo = new Modulo("A", "no");
        modulo.setId(1L);
        moduloRep.save(modulo);
        Modulo found = moduloRep.getOne(modulo.getId());
        assertTrue(found!=null);
        assertEquals(found.getGruppoSanguigno(),modulo.getGruppoSanguigno());
        assertEquals(found.getFumatore(),modulo.getFumatore());
    }

    @Test
    public void EmergenzaTest(){
        CentroTrasfusione centro =centroRep.save(new CentroTrasfusione("mar","mar","centro"));
        Emergenza emergenza = emergenzaRep.save(new Emergenza(centro, "A"));
        Emergenza found = emergenzaRep.getOne(emergenza.getId());
        assertTrue(found!=null);
        assertEquals(found.getGruppoSanguigno(),emergenza.getGruppoSanguigno());
        assertEquals(found.getIdCentroTrasfusione().getId(),centro.getId());
        assertEquals(emergenza.getId(), found.getId());
    }
}