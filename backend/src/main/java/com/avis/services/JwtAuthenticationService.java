package com.avis.services;

import java.util.ArrayList;
import java.util.Collection;
import com.avis.repositories.JwtAuthenticationRepository;
import com.avis.security.dto.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    //@Autowired
    //private JwtAuthenticationRepository jwtAuthRepository;

    static Collection<SimpleGrantedAuthority> auth = new ArrayList<>();

    static{
        auth.add(new SimpleGrantedAuthority("role1"));
    }

    public JwtUser findUser(String email,String pw) {
        /* prendo un record dal DB e ci creo un dto->JwtUser,
         più avanti dovrò aggiungere anche username nel costruttore */
        return new JwtUser("email", "password", "username" , auth, true);
    }       
}
