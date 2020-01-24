package com.avis;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avis.dto.CredenzialiDto;
import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;

import com.avis.models.Modulo;
import com.avis.models.Prenotazione;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.DonatoreRepository;

import com.avis.repositories.ModuloRepository;
import com.avis.repositories.PrenotazioniRepository;
import com.avis.repositories.SedeAvisRepository;

import com.avis.services.ProfiloService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@PropertySource(value = "classpath:application-test.properties")
public class ProfiloServiceTest {

    @TestConfiguration
    static class ProfiloServiceTestContextConfig {
        @Bean
        public ProfiloService profiloService() {
            return new ProfiloService();
        }
    }

    @Autowired
    private ProfiloService profiloService;
    @MockBean
    private ModuloRepository moduloRepository;
    @MockBean
    private DonatoreRepository donatoreRepository;
    @MockBean
    private SedeAvisRepository sedeAvisRepository;
    @MockBean
    private CentroTrasfusioneRepository centroRepository;
    @MockBean
    private PrenotazioniRepository prenotazioneRepository;


    @Before
    public void setup() {
        Donatore donatore = new Donatore("donatore@don.it","123123","donatore","leonardo","rossi");
        Optional<Donatore> optionalDonatore =  Optional.of(donatore);
        Modulo modulo = new Modulo("A","A","A","A","A","A","A","A","A");
        Optional<Modulo> optionalModulo = Optional.of(modulo);
        SedeAvis sedeAvis = new SedeAvis("sede@avis.it", "123123", "sedeAvis", "Marche", "Macerata", "Morrovalle");
        Optional<SedeAvis> optionalSede = Optional.of(sedeAvis);
        CentroTrasfusione centroTrasfusione = new CentroTrasfusione("centro@trasfusione.it", "123123", "centroTrasfusione", "Marche", "Macerata", "Morrovalle");
        Optional<CentroTrasfusione> optionalCentro = Optional.of(centroTrasfusione);
        Prenotazione prenotazione = new Prenotazione(sedeAvis, new Timestamp(430430430));
        List<Prenotazione> listPrenotazioni = new ArrayList<>(); listPrenotazioni.add(prenotazione);
        long id = 0; String emailDonatore = "donatore@don.it";
        Optional<List<Prenotazione>> optionalListPrenotazioni = Optional.of(listPrenotazioni);
        Mockito.when(donatoreRepository.findById(id)).thenReturn(optionalDonatore);
        Mockito.when(sedeAvisRepository.findById(id)).thenReturn(optionalSede);
        Mockito.when(centroRepository.findById(id)).thenReturn(optionalCentro);
        Mockito.when(donatoreRepository.findByEmail(emailDonatore)).thenReturn(donatore);
        Mockito.when(moduloRepository.findById(id)).thenReturn(optionalModulo);
        Mockito.when(prenotazioneRepository.findByIdDonatore(donatore)).thenReturn(optionalListPrenotazioni);
    }

    
    @Test
    public void showInfoTest(){

        Utente utente = profiloService.showInfo(new Utente("donatore@don.it","123123","donatore"));
        assertTrue(utente instanceof Donatore);
        Donatore donatore = (Donatore) utente;
        assertTrue(donatore.getNome()=="leonardo");
        assertTrue(donatore.getCognome()=="rossi");
        utente = profiloService.showInfo(new Utente("sede@avis.it","123123","sedeAvis"));
        assertTrue(utente instanceof SedeAvis);
        SedeAvis sedeAvis = (SedeAvis) utente;
        assertTrue(sedeAvis.getRegione()=="Marche");
        assertTrue(sedeAvis.getProvincia()=="Macerata");
        assertTrue(sedeAvis.getComune()=="Morrovalle");
        utente = profiloService.showInfo(new Utente("centro@trasfusione.it","123123","centroTrasfusione"));
        assertTrue(utente instanceof CentroTrasfusione);
        CentroTrasfusione centroTrasfusione = (CentroTrasfusione) utente;
        assertTrue(centroTrasfusione.getRegione()=="Marche");
        assertTrue(centroTrasfusione.getProvincia()=="Macerata");
        assertTrue(centroTrasfusione.getComune()=="Morrovalle");
        utente = profiloService.showInfo(new Utente("e@e.it","1212","null"));
        assertTrue(utente == null);
    }

    @Test
    public void modificaCredenzialiTest(){
        CredenzialiDto credenzialiDto = new CredenzialiDto();
        //cambiata meail e cognome
        credenzialiDto.setDonatore(new Donatore("don@donatore.it","123123","donatore","leonardo","bianchi"));
        profiloService.modificaCredenziali(credenzialiDto, new Utente("donatore@don.it","123123","donatore"));
    }







}