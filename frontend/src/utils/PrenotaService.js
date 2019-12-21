import axios from 'axios'

const API_URL = 'http://localhost:8080'

class PrenotaService{

    search(comune){
        return axios.post(API_URL+'/public/prenotazione',
        comune,
        {headers: {"Content-Type": "text/plain"}
        });       
    }

    prenota(prenotazioneDto){
        return axios.put(API_URL+'/public/prenotazione',
        prenotazioneDto,
        {headers: {"Content-Type": "application/json"}
        });       
    }

    getRegioni(){
        return axios.post(API_URL+'/public/getRegioni',
        {headers: {"Content-Type": "application/json"}
        });       
    }

    getProvincie(regione){
        return axios.post(API_URL+'/public/getProvincie', regione, 
        {headers: {"Content-Type": "application/json"}
        });       
    }

    getComuni(provincia){
        return axios.post(API_URL+'/public/getComuni', provincia, 
        {headers: {"Content-Type": "application/json"}
        });       
    }
}

export default new PrenotaService()