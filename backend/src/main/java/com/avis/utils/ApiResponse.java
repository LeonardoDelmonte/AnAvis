package com.avis.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.avis.models.Emergenza;
import com.avis.models.Prenotazione;
import com.avis.models.Utente;

import org.springframework.http.HttpStatus;

/**
 * ApiResponse
 */
public class ApiResponse implements InterfaceApi {

    private Utente utente;
    private String token, message;
    private Prenotazione prenotazione;
    private List<Prenotazione> listPrenotazione;
    private Map<String, List<Timestamp>> map;
    private HttpStatus status;
    private Set<String> set;
    // sta cosa credo non vada bene, non si può mettere list per la type erasure :(
    private ArrayList<Emergenza> listEmergenze;

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

    // insert date
    public ApiResponse(List<Timestamp> listOK, List<Timestamp> listError) {
        this.map = new HashMap<>();
        map.put("listOK", listOK);
        map.put("listError", listError);
        this.status = HttpStatus.OK;
    }

    // get date libere
    public ApiResponse(List<Prenotazione> list) {
        this.listPrenotazione = list;
    }

    // get emergenze
    public ApiResponse(ArrayList<Emergenza> listEmergenze) {
        this.listEmergenze = listEmergenze;
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

    public List<Prenotazione> getListPrenotazione() {
        return listPrenotazione;
    }

    public void setListPrenotazione(List<Prenotazione> listPrenotazione) {
        this.listPrenotazione = listPrenotazione;
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

    public Map<String, List<Timestamp>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<Timestamp>> map) {
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

    public ArrayList<Emergenza> getListEmergenze() {
        return listEmergenze;
    }

    public void setListEmergenze(ArrayList<Emergenza> listEmergenze) {
        this.listEmergenze = listEmergenze;
    }

}