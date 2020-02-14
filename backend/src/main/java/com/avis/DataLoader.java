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
    private PrenotazioniService prenotazione;
    @Autowired
    private EmergenzaService emergenza;
    @Autowired
    private ProfiloService profilo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //password -> marco

        sedeAvis.save(new SedeAvis("sede1","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","sedeAvis","marche","macerata","morrovalle"));
        sedeAvis.save(new SedeAvis("sede2","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","sedeAvis","marche","macerata","montecosaro"));
        donatore.save(new Donatore("marco","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","donatore", "marco", "scarpa"));
        donatore.save(new Donatore("leo","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","donatore","leo", "del"));
        donatore.save(new Donatore("lore","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","donatore","lore", "rebi"));
        centroTrasf.save(new CentroTrasfusione("centro1","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","centroTrasfusione","marche","pesaro","tavullia"));
        centroTrasf.save(new CentroTrasfusione("centro2","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","centroTrasfusione","marche","pesaro","fano"));
        centroTrasf.save(new CentroTrasfusione("centro3","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","centroTrasfusione","marche","pesaro","bhoo"));
        donatore.save(new Donatore("ugo","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","donatore","ugo", "spina"));
        donatore.save(new Donatore("ric","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","donatore","ric", "ric"));
        sedeAvis.save(new SedeAvis("sede3","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","sedeAvis","abruzzo","pescara","pescara"));
        sedeAvis.save(new SedeAvis("sede4","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","sedeAvis","abruzzo","aquila","aquila"));
        sedeAvis.save(new SedeAvis("sede5","$2a$10$x1VGNaJf8AHJAslOnPsh1e9OufSxWor0OwSDfORdD98GDlXzwMDN6","sedeAvis","lazio","roma","civitavecchia"));
        Timestamp ts1 = Timestamp.valueOf("2020-04-13 11:00:00");
        Timestamp ts2 = Timestamp.valueOf("2020-04-13 12:00:00");
        Timestamp ts5 = Timestamp.valueOf("2020-06-20 11:30:00");
        Timestamp ts4 = Timestamp.valueOf("2020-06-20 12:30:00");
        Timestamp ts3 = Timestamp.valueOf("2020-04-13 13:00:00");
        Timestamp ts8 = Timestamp.valueOf("2020-03-13 13:00:00");
        Timestamp ts9 = Timestamp.valueOf("2020-03-13 15:00:00");
        Timestamp ts10 = Timestamp.valueOf("2020-02-08 11:00:00");
        Timestamp ts11 = Timestamp.valueOf("2020-02-08 12:00:00");
        Modulo uno = new Modulo("scrmrc", "via roma 3", "333-2211", "00", "no", "no", "si", "si", "no");
        uno.setId(1L);
        Modulo due = new Modulo("lnrdlm", "via cascia 99", "444-3232", "AA", "no", "si", "si", "no", "no");
        due.setId(2L);
        Modulo tre = new Modulo("lrnrbc", "via piave 50", "444-3232", "AA", "no", "si", "si", "no", "no");
        tre.setId(3L);
        profilo.modificaModulo(uno,"marco");
        profilo.modificaModulo(due, "leo");
        profilo.modificaModulo(tre, "lore");
        prenotazione.save(new DateDto(ts1,ts2), (long)1);
        prenotazione.save(new DateDto(ts5,ts4), (long)1);
        prenotazione.save(new DateDto(ts2,ts3), (long)2);
        prenotazione.save(new DateDto(ts8,ts9), (long)1);
        prenotazione.save(new DateDto(ts10,ts11), (long)2);
        //prenotazione.prenotaData(new PrenotazioneDto((long)3, "marco"));
        prenotazione.prenotaData(new PrenotazioneDto((long)14, "leo"));
        prenotazione.prenotaData(new PrenotazioneDto((long)7, "lore"));
        emergenza.save("A Rh-", (long)7);
        emergenza.save("A Rh-", (long)6);
        emergenza.save("0 Rh+", (long)6);  
        emergenza.save("0 Rh-", (long)7);
        emergenza.save("AB Rh+", (long)6); 
        emergenza.save("AB Rh+", (long)7); 
        emergenza.save("B Rh+", (long)7);
        emergenza.save("B Rh+", (long)6);
        emergenza.save("B Rh+", (long)8);  
    }

}