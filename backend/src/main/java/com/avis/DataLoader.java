package com.avis;

import java.sql.Timestamp;

import com.avis.dto.DateDto;
import com.avis.dto.PrenotazioneDto;
import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.Modulo;
import com.avis.models.SedeAvis;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.SedeAvisRepository;
import com.avis.services.EmergenzaService;
import com.avis.services.PrenotazioniService;
import com.avis.services.ProfiloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private DonatoreRepository donatore;
    @Autowired
    private SedeAvisRepository sedeAvis;
    @Autowired
    private CentroTrasfusioneRepository centroTrasf;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private PrenotazioniService prenotazione;
    @Autowired
    private EmergenzaService emergenza;
    @Autowired
    private ProfiloService profilo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        donatore.save(new Donatore("marco",bcryptEncoder.encode("marco"),"donatore"));
        donatore.save(new Donatore("leo",bcryptEncoder.encode("leo"),"donatore"));
        SedeAvis sede1 = new SedeAvis("sede1",bcryptEncoder.encode("sede1"),"sedeAvis");
        sede1.setRegione("marche");sede1.setProvincia("macerata");sede1.setComune("morrovalle");
        SedeAvis sede2 = new SedeAvis("sede2",bcryptEncoder.encode("sede2"),"sedeAvis");
        sede2.setRegione("marche");sede2.setProvincia("macerata");sede2.setComune("montecosaro");
        sedeAvis.save(sede1); sedeAvis.save(sede2);
        centroTrasf.save(new CentroTrasfusione("centro1",bcryptEncoder.encode("centro1"),"centroTrasfusione"));
        centroTrasf.save(new CentroTrasfusione("centro2",bcryptEncoder.encode("cetro1"),"centroTrasfusione"));
        Timestamp ts1 = Timestamp.valueOf("2020-01-13 11:00:00");
        Timestamp ts2 = Timestamp.valueOf("2020-01-13 12:00:00");
        Timestamp ts5 = Timestamp.valueOf("2020-01-13 11:30:00");
        Timestamp ts4 = Timestamp.valueOf("2020-01-13 12:30:00");
        Timestamp ts3 = Timestamp.valueOf("2020-01-13 13:00:00");
        profilo.modificaModulo(new Modulo("B", "no"), (long)1);
        profilo.modificaModulo(new Modulo("A", "si"), (long)2);
        prenotazione.save(new DateDto(ts1,ts2), (long)3);
        prenotazione.save(new DateDto(ts5,ts4), (long)3);
        prenotazione.save(new DateDto(ts2,ts3), (long)4);
        prenotazione.prenotaData(new PrenotazioneDto((long)3, "marco"));
        prenotazione.prenotaData(new PrenotazioneDto((long)6, "leo"));
        emergenza.save("A", (long)5);
        emergenza.save("00", (long)6);
        

    }

}