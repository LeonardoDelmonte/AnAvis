import axios from 'axios'

const API_URL = 'http://localhost:8080'

class PrenotaService{

    search(sede){
        return axios.post(API_URL+'/prenota',
        sede,
        {headers: {"Content-Type": "text/plain"}
        });       
    }

    prenota(id){
        return axios.post(API_URL+'/prenota/data',
        id,
        {headers: {"Content-Type": "application/json"}
        });       
    }
}

export default new PrenotaService()