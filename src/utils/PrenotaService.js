import axios from 'axios'

const API_URL = 'http://localhost:8080'

class PrenotaService{

    search(){
        return axios.get(API_URL+'/prenota');       
    }
}

export default new PrenotaService()