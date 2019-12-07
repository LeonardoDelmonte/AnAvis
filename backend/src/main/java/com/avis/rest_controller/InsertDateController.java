package com.avis.rest_controller;
import com.avis.models.Prenotazione;
import com.avis.services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
public class InsertDateController{
    //private List<GregorianCalendar> listOrari = new ArrayList<>();
    
    @Autowired
    private DateService dateService;

    @PostMapping("/inserisciDate")
    public boolean inserisciDate(@RequestBody Prenotazione dataLibera){
        //formattaData(dt.hourStart,minuteStart,hourFinish,minuteFinish);
        //for(GregorianCalendar orario : listOrari){
        dateService.saveNewDate(dataLibera);
        //}
        return true;    
    }

     
    //private boolean formattaData(int hourS,int minuteS,int hourF,int minuteF){
    //    listOrari.add(new GregorianCalendar(1, 1, 1, hourS, minuteS));
    //    return true;
    //}
    
    
}
