package com.avis.rest_controller;
import java.util.List;
import com.avis.models.Prenotazione;
import com.avis.services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins={"*"})
@RestController
public class PrenotazioneController{

    @Autowired
    private DateService dateService;

    @PostMapping("/prenota")
    public List<Prenotazione> searchSede(@RequestBody String sede){
        System.out.println(sede);
        return dateService.findBySedeAvis(sede);
    } 

    @PostMapping("/prenota/data")
    public boolean prenotaData(@RequestBody int id){
        dateService.prenotaData(id);
        return true;
    }

}
