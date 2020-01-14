import axios from 'axios'
const API_URL = 'http://localhost:8080'

class ProfiloService {

    loadProfilo() {
        var config = {
            headers: {'Authorization': localStorage.getItem('Authorization')}
        };
        return axios.get(
            API_URL + '/profilo/showInfo',
            config
        );
    }

    modificaProfilo(res){
        var config = {
            headers: {'Authorization': localStorage.getItem('Authorization')}
        };
        
        return axios.put(
            API_URL + '/profilo/modificaCredenziali',
            res,
            config
        );
    }

   
}

export default new ProfiloService()