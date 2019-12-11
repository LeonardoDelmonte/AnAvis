package com.avis.services;
import java.util.ArrayList;
import java.util.List;
import com.avis.models.Utente;
import com.avis.repositories.JwtAuthenticationRepository;
import com.avis.security.dto.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


@Service
public class JwtAuthenticationService {

    @Autowired
    private JwtAuthenticationRepository jwtAuthRepository;

    //sarà meglio fare la connessione al DB e una query oppure fare così??? 
    public JwtUser findUser(String email,String pw) {       
        List<Utente> listaUtenti = new ArrayList<>();
        listaUtenti = jwtAuthRepository.findAll();
        listaUtenti.stream().filter(e -> e.getEmail().compareTo(email)==0);
        if(!(listaUtenti.size()==1))
            return null;
        else{
            if(listaUtenti.get(0).getPassword().compareTo(pw)!=0){
                return null;
            }
            else{
                /* controllare il flag, creare la lista di authorized dato il flag,
                creare il JWT da tornare indietro...la lista mi rode, poi porvo 
                a fare Override di UserDetails */
                List<SimpleGrantedAuthority> auth = new ArrayList<>();
                auth.add(new SimpleGrantedAuthority("role1"));
                return new JwtUser("email", "password", "username", auth , true);
            }
        }
    }
}
