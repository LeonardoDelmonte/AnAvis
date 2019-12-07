package com.avis.rest_controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins={"*"})
@RestController
public class HomeController{


    @GetMapping("/home")
    public void getHome(){
        
    }

//probabilmente non va nel backend;
//    @GetMapping("/home")
//    public NewsAvis getHome(){
//        return APINewsAvis();
//    }
//    

    
}
