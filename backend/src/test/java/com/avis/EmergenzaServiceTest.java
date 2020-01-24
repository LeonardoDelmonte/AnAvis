package com.avis;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.avis.models.CentroTrasfusione;
import com.avis.models.Emergenza;

import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.EmergenzaRepository;

import com.avis.services.EmergenzaService;


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
public class EmergenzaServiceTest {

    @TestConfiguration
    static class EmergenzaServiceTestContextConfig {
        @Bean
        public EmergenzaService emergenzaService() {
            return new EmergenzaService();
        }
    }

    @Autowired
    private EmergenzaService emergenzaService;

    @MockBean
    private CentroTrasfusioneRepository centroTrasfusioneRepository;
    @MockBean
    private EmergenzaRepository emergenzaRepository;

    @Before
    public void setup() {
        CentroTrasfusione centroTrasfusione = new CentroTrasfusione("sede@avis.it", "123123", "centroTrasfusione", "Marche", "Macerata", "Morrovalle");      
        Emergenza emergenza = new Emergenza(centroTrasfusione,"A");
        Optional<CentroTrasfusione> optianlCentro = Optional.of(centroTrasfusione); 
        long id = 0 ;
        Mockito.when(centroTrasfusioneRepository.findById(id)).thenReturn(optianlCentro);
        Optional<Emergenza> optionalEmergenza = Optional.of(emergenza);
        Mockito.when(emergenzaRepository.findById(id)).thenReturn(optionalEmergenza);
        List<Emergenza> listEmergenza = new ArrayList<>(); listEmergenza.add(emergenza);
        Optional<List<Emergenza>> optionalListEmerg = Optional.of(listEmergenza);
        Mockito.when(emergenzaRepository.findByIdCentroTrasfusione(centroTrasfusione)).thenReturn(optionalListEmerg);
        List<CentroTrasfusione> listCentro = new ArrayList<>(); listCentro.add(centroTrasfusione);
        Mockito.when(centroTrasfusioneRepository.findAll()).thenReturn(listCentro);
        List<Emergenza> listEmerg = new ArrayList<>(); listEmerg.add(emergenza);
        Mockito.when(emergenzaRepository.findAll()).thenReturn(listEmerg);
        
    }

    @Test
    public void saveEmergenzaTest() {
        List<CentroTrasfusione> list = centroTrasfusioneRepository.findAll();
        CentroTrasfusione centro = list.get(0);
        emergenzaService.save("A",centro.getId());
        List<Emergenza> listEmerg = emergenzaRepository.findAll();
        assertTrue(listEmerg.size()==1);
        assertTrue(listEmerg.get(0).getGruppoSanguigno()=="A");
    }


    @Test
    public void deleteEmergenzaTest() {
        List<CentroTrasfusione> list = centroTrasfusioneRepository.findAll();
        CentroTrasfusione centro = list.get(0);
        emergenzaService.save("A",centro.getId());
        long id = 0;
        assertTrue(emergenzaService.delete(id));
    }


    @Test
    public void getEmergenzeTest() {
        List<CentroTrasfusione> list = centroTrasfusioneRepository.findAll();
        CentroTrasfusione centro = list.get(0);
        List<Emergenza> listEmerg = emergenzaService.getEmergenze(centro.getId());
        assertTrue(listEmerg.size()==1);
    }
    
}