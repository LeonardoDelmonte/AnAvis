import axios from 'axios'
const API_URL = 'http://localhost:8080'

class CentroTrasfusioneService {

    inviaEmergenza(gruppoSanguigno) {
        var config = {
            headers: {'Authorization': localStorage.getItem('Authorization')}
        };
        return axios.post(
            API_URL + '/requestEmerg/insert',
            gruppoSanguigno,
            config
        );
    }

   
}

export default new CentroTrasfusioneService()