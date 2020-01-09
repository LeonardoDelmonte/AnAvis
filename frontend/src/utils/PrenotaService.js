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
        var config = {
            headers: {'Authorization': "bearer " + localStorage.getItem('Authorization')}
        };
        var bodyParameters = {
        }

        return axios.post(
            API_URL+'/prenotazione/getRegioni',
            bodyParameters,
            config
          )
    }

    getProvince(regione){
        return axios.post(API_URL+'/public/getProvince', regione, 
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