import axios from 'axios'

const API_URL = 'http://localhost:8080'

class PrenotaService{

    search(comune){
        return axios.post(API_URL+'/prenota',
        comune,
        {headers: {"Content-Type": "text/plain"}
        });       
    }

    prenota(id){
        return axios.post(API_URL+'/prenota/data',
        id,
        {headers: {"Content-Type": "application/json"}
        });       
    }

    getRegioni(){
        return axios.post(API_URL+'/getRegioni',
        {headers: {"Content-Type": "application/json"}
        });       
    }

    getProvincie(regione){
        return axios.post(API_URL+'/getProvincie', regione, 
        {headers: {"Content-Type": "application/json"}
        });       
    }

    getComuni(provincia){
        return axios.post(API_URL+'/getComuni', provincia, 
        {headers: {"Content-Type": "application/json"}
        });       
    }
}

export default new PrenotaService()