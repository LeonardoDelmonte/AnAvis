package com.avis;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.avis.dto.DateDto;
import com.avis.models.Donatore;
import com.avis.models.Prenotazione;
import com.avis.models.SedeAvis;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.PrenotazioniRepository;
import com.avis.repositories.SedeAvisRepository;
import com.avis.services.PrenotazioniService;
import com.avis.services.ProfiloService;
import com.avis.utils.ApiResponse;

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
public class PrenotazioneServiceTest {

    @TestConfiguration
    static class SedeAvisServiceTestContextConfig {
        @Bean
        public PrenotazioniService prenotazioniService() {
            return new PrenotazioniService();
        }
    }

    @TestConfiguration
    static class ProfiloServiceTestContextConfig {
        @Bean
        public ProfiloService profiloService() {
            return new ProfiloService();
        }
    }

    @Autowired
    private PrenotazioniService prenotazioniService;

    @Autowired
    private ProfiloService profiloService;

    @MockBean
    private DonatoreRepository donatoreRepository;

    @MockBean
    private SedeAvisRepository sedeRepository;

    @MockBean
    private PrenotazioniRepository prenotazioniRepository;

    @Before
    public void setup() {
        SedeAvis sedeAvis = new SedeAvis("sede@avis.it", "123123", "sedeAvis", "Marche", "Macerata", "Morrovalle");
        Donatore donatore = new Donatore("dona@dona.it", "123123", "donatore", "marco", "scarpa");
        Optional<SedeAvis> sede = Optional.of(sedeAvis);
        Mockito.when(sedeRepository.findById(0L)).thenReturn(sede);
        Timestamp ts1 = Timestamp.valueOf("2020-02-13 11:00:00");
        Timestamp ts2 = Timestamp.valueOf("2020-02-13 11:15:00");
        Timestamp ts3 = Timestamp.valueOf("2020-02-13 11:30:00");
        Prenotazione p1 = new Prenotazione(sedeAvis, ts1);
        Prenotazione p2 = new Prenotazione(sedeAvis, ts2);
        Mockito.when(prenotazioniRepository.findByIdSedeAvisAndDate(sedeAvis, ts1)).thenReturn(Arrays.asList(p1));
        Mockito.when(prenotazioniRepository.findByIdSedeAvisAndDate(sedeAvis, ts2)).thenReturn(Arrays.asList(p2));
        Mockito.when(prenotazioniRepository.findByIdSedeAvisAndDate(sedeAvis, ts3)).thenReturn(new ArrayList<>());
        Optional<Prenotazione> p3 = Optional.of(p1);
        Mockito.when(prenotazioniRepository.findById(0L)).thenReturn(p3);
        Mockito.when(prenotazioniRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Mockito.when(sedeRepository.findByComune("Morrovalle")).thenReturn(sedeAvis);
        Mockito.when(prenotazioniRepository.findByIdSedeAvisAndDateBetween(sedeAvis, ts1, ts2)).thenReturn(Arrays.asList(p1,p2));


    
    }

    @Test
    public void insertDate_Test() {
        Timestamp ts1 = Timestamp.valueOf("2020-02-13 11:00:00");
        Timestamp ts3 = Timestamp.valueOf("2020-02-13 11:30:00");
        Timestamp ts4 = Timestamp.valueOf("2020-02-13 11:45:00");
        DateDto date = new DateDto(ts1, ts4);
        ApiResponse<Timestamp> response = prenotazioniService.save(date, 0L);
        List<Timestamp> list1 = response.getMap().get("listOK");
        List<Timestamp> list2 = response.getMap().get("listError");
        assertTrue(list2.get(0).getTime()==ts1.getTime());
        assertTrue(list1.size()==1);
        assertTrue(list2.size()==2);
        assertTrue(list1.get(0).getTime()==ts3.getTime());
        
    }

    @Test
    public void deleteDate_Test() {
        assertTrue(prenotazioniService.deleteDate(0L));
        assertFalse(prenotazioniService.deleteDate(1L));
        
    }

    @Test
    public void getDateLibere_Test() {
        Timestamp ts1 = Timestamp.valueOf("2020-02-13 11:00:00");
        Timestamp ts2 = Timestamp.valueOf("2020-02-13 11:15:00");
        DateDto date = new DateDto(ts1, ts2);
        date.setComune("Morrovalle");
        List<Prenotazione> list = prenotazioniService.getDateLibere(date);
        assertTrue(list.get(0).getDate().getTime()==ts1.getTime());
        assertTrue(list.get(1).getDate().getTime()==ts2.getTime());
        
    }
}