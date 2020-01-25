package com.avis.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.avis.models.Modulo;
import com.avis.models.Prenotazione;
import com.avis.models.Utente;

import org.springframework.http.HttpStatus;

/**
 * ApiResponse
 * 
 * @param <T>
 */
public class ApiResponse<T> implements InterfaceApi {

    private Utente utente;
    private String token, message;
    private Prenotazione prenotazione;
    private List<T> list;
    private Map<String, List<T>> map;
    private HttpStatus status;
    private Set<String> set;
    private Modulo modulo;

    // per il login
    public ApiResponse(Utente utente, String token) {
        this.utente = utente;
        this.token = token;
    }

    // per i put profilo
    public ApiResponse(String message, Utente utente) {
        this.utente = utente;
        this.message = message;
    }

    // prenota data
    public ApiResponse(String message, Prenotazione prenotazione) {
        this.message = message;
        this.prenotazione = prenotazione;
        this.status = HttpStatus.OK;
    }

    // insert date - getPrenotazioni
    public ApiResponse(String str1, List<T> list1, String str2, List<T> list2) {
        this.map = new HashMap<>();
        map.put(str1, list1);
        map.put(str2, list2);
        this.status = HttpStatus.OK;
    }

    // get date libere, get emergenza
    public ApiResponse(List<T> list) {
        this.list = list;
    }

    public ApiResponse(Modulo modulo){
        this.modulo = modulo;
    }
    
    // response info
    public ApiResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(Set<String> set) {
        this.set = set;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<T> getList() {
        return list;
    }

    public void setListPrenotazione(List<T> list) {
        this.list = list;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }

    public Map<String, List<T>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<T>> map) {
        this.map = map;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }


    

}