package com.avis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Optional;

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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@PropertySource(value = "classpath:application-test.properties")
public class RepositoryTest {

    @Autowired
    private TestEntityManager manager;
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
    public void donatoreTest() {
        Donatore don = manager.persist(new Donatore("ric", "ric", "donatore", "ricca", "colt"));
        Utente utente = utenteRep.findByEmail(don.getEmail());
        Donatore test = donatoreRep.findByEmail(don.getEmail());
        assertTrue(utente != null);
        assertTrue(test != null);
        assertEquals(test.getId(), utente.getId());
        assertEquals(don.getId(), utente.getId());
        assertEquals(don.getEmail(), utente.getEmail());
        assertEquals(don.getRuolo(), utente.getRuolo());
        assertEquals(don.getNome(), test.getNome());
        assertEquals(don.getCognome(), test.getCognome());
    }

    @Test
    public void sedeAvisTest() {
        SedeAvis sede = manager.persist(new SedeAvis("leo", "leo", "sedeAvis", "marche", "macerata", "morrovalle"));
        Utente utente = utenteRep.findByEmail(sede.getEmail());
        Optional<SedeAvis> test = sedeAvisRep.findById(sede.getId());
        assertTrue(utente != null);
        assertTrue(test.isPresent());
        assertEquals(test.get().getId(), utente.getId());
        assertEquals(sede.getId(), utente.getId());
        assertEquals(sede.getPassword(), utente.getPassword());
        assertEquals(sede.getRuolo(), utente.getRuolo());
        assertEquals(sede.getRegione(), test.get().getRegione());
        assertEquals(sede.getProvincia(), test.get().getProvincia());
        assertEquals(sede.getComune(), test.get().getComune());
    }

    @Test
    public void CentroTest() {
        CentroTrasfusione centro = manager
                .persist(new CentroTrasfusione("lore", "lore", "centro", "marche", "macerata", "montecosaro"));
        Utente utente = utenteRep.findByEmail(centro.getEmail());
        Optional<CentroTrasfusione> test = centroRep.findById(centro.getId());
        assertTrue(test.isPresent());
        assertTrue(utente != null);
        assertEquals(test.get().getId(), utente.getId());
        assertEquals(centro.getId(), utente.getId());
        assertEquals(centro.getEmail(), utente.getEmail());
        assertEquals(centro.getRuolo(), utente.getRuolo());
    }

    @Test
    public void PrenotazioneTest() {
        Timestamp time = Timestamp.valueOf("2020-08-14 11:00:00");
        SedeAvis sede = manager.persist(new SedeAvis("pippo", "pippo", "sedeAvis", "marche", "macerata", "morrovalle"));
        Prenotazione prenotazione = manager.persist(new Prenotazione(sedeAvisRep.getOne(sede.getId()), time));
        Prenotazione found = prenotazioniRep.getOne(prenotazione.getIdPrenotazione());
        assertTrue(prenotazione != null);
        assertEquals(found.getIdPrenotazione(), prenotazione.getIdPrenotazione());
        assertEquals(sede.getId(), prenotazione.getIdSedeAvis().getId());
        assertEquals(time.toString(), prenotazione.getDate().toString());
    }

    @Test
    public void ModuloTest() {
        Modulo modulo = new Modulo("scrmrc", "via roma 3", "333-2211", "00", "no", "no", "si", "si", "no");
        manager.persist(modulo);
        Modulo found = moduloRep.getOne(modulo.getId());
        assertTrue(found != null);
        assertEquals(found.getId(), modulo.getId());
        assertEquals(found.getGruppoSanguigno(), modulo.getGruppoSanguigno());
        assertEquals(found.getFumatore(), modulo.getFumatore());
    }

    @Test
    public void EmergenzaTest() {
        CentroTrasfusione centro = manager
                .persist(new CentroTrasfusione("mar", "mar", "centro", "marche", "macerata", "morrovalle"));
        Emergenza emergenza = manager.persist(new Emergenza(centro, "A"));
        Emergenza found = emergenzaRep.getOne(emergenza.getId());
        assertTrue(found != null);
        assertEquals(found.getGruppoSanguigno(), "A");
        assertEquals(found.getIdCentroTrasfusione().getId(), centro.getId());
        assertEquals(emergenza.getId(), found.getId());
    }
}