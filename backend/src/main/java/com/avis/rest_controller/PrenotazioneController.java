package com.avis.rest_controller;
import java.util.Optional;
import com.avis.models.Prenotazione;
import com.avis.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins={"*"})
@RestController
public class PrenotazioneController{

    @Autowired
    private PrenotazioniService prenotazioniService;

    @PostMapping("/prenota")
    public Optional<Prenotazione> searchSede(@RequestBody String sede){
        return prenotazioniService.findBySedeAvis(sede);
    } 

    @PostMapping("/prenota/data")
    public boolean prenotaData(@RequestBody int id){
        prenotazioniService.prenotaData(id);
        return true;
    }

    //@PostMapping("/getRegioni")
    //public Set<String> searchRegioni(){
    //    return sedeService.retrieveAllRegions();
    //}
//
    //@PostMapping("/getProvincie")
    //public Set<String> searchProvincie(@RequestBody String regione){
    //    return sedeService.retrieveAllProvincie(regione);
    //}
//
    //@PostMapping("/getComuni")
    //public Set<String> searchComuni(@RequestBody String provincia){
    //    return sedeService.retrieveAllComuni(provincia);
    //}



}
