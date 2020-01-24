package com.avis;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.avis.models.SedeAvis;
import com.avis.repositories.SedeAvisRepository;
import com.avis.services.SedeAvisService;

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
public class SedeAvisServiceTest {

    @TestConfiguration
    static class SedeAvisServiceTestContextConfig {
        @Bean
        public SedeAvisService sedeAvisService() {
            return new SedeAvisService();
        }
    }

    @Autowired
    private SedeAvisService sedeAvisService;

    @MockBean
    private SedeAvisRepository sedeAvisRepository;

    @Before
    public void setup() {
        SedeAvis sedeAvis = new SedeAvis("sede@avis.it", "123123", "sedeAvis", "Marche", "Macerata", "Morrovalle");
        List<SedeAvis> list = new ArrayList<>();
        list.add(sedeAvis);
        sedeAvisRepository.save(sedeAvis);
        Mockito.when(sedeAvisRepository.findAll()).thenReturn(list);
        Optional<List<SedeAvis>> listOptional = Optional.of(list);
        Mockito.when(sedeAvisRepository.findByRegione("Marche")).thenReturn(listOptional);
        Mockito.when(sedeAvisRepository.findByProvincia("Macerata")).thenReturn(listOptional);
    }

    @Test
    public void findSedeAvis_checkRegioni() {
        String regione = "Marche";
        Set<String> sedeAvis = sedeAvisService.getRegioni();
        assertTrue(sedeAvis.contains(regione));
    }


    @Test
    public void findSedeAvis_checkProvincia() {
        String regione = "Marche";
        String provincia = "Macerata";
        Set<String> sedeAvis = sedeAvisService.getProvince(regione);
        assertTrue(sedeAvis.contains(provincia));
    }


    @Test
    public void findSedeAvis_checkComuni() {
        String provincia = "Macerata";
        String comune = "Morrovalle";
        Set<String> sedeAvis = sedeAvisService.getComuni(provincia);
        assertTrue(sedeAvis.contains(comune));
    }

    @Test
    public void findSedeAvis_checkParameters(){
        List<SedeAvis> listSedeAvis = sedeAvisRepository.findAll();
        SedeAvis sedeAvis = listSedeAvis.get(0);
        assertTrue(sedeAvis.getId()==0);
        assertTrue(sedeAvis.getRuolo()=="sedeAvis");
        assertTrue(sedeAvis.getEmail()=="sede@avis.it");
        assertTrue(sedeAvis.getPassword()=="123123");
    }

    
}