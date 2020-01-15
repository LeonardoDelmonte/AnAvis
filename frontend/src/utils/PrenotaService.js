import axios from 'axios'

const API_URL = 'http://localhost:8080'

class PrenotaService {

    search(getDateDto) {
        var config = {
            headers: { 'Authorization': localStorage.getItem('Authorization'), 
                    'Content-Type': 'application/json' }
        };
        return axios.post(
            API_URL + '/prenotazione/',
            getDateDto,
            config
        );
    }

    prenota(idPrenotazione, donatore) {
        var config = {
            headers: { 'Authorization': localStorage.getItem('Authorization'), 'Content-Type': 'application/json' }
        };
        //non sapevo dove metterla, veditela tu <3
        var prenotazioneDto = {
            'idDataLibera': idPrenotazione,
            'emailDonatore': donatore
        }
        return axios.put(
            API_URL + '/prenotazione/donatore',
            prenotazioneDto,
            config
        );
    }

    getRegioni() {
        var config = {
            headers: { 'Authorization': localStorage.getItem('Authorization') }
        };
        return axios.get(
            API_URL + '/prenotazione/getRegioni',
            config
        )
    }

    getProvince(regione) {
        var config = {
            headers: { 'Authorization': localStorage.getItem('Authorization'), 'Content-Type': 'application/json' }
        };
        return axios.get(
            API_URL + '/prenotazione/getProvince/'+regione,
            config
        );
    }

    getComuni(provincia) {
        var config = {
            headers: { 'Authorization': localStorage.getItem('Authorization'), 'Content-Type': 'application/json' }
        };
        return axios.get(
            API_URL + '/prenotazione/getComuni/'+provincia,
            config
        );
    }
}

export default new PrenotaService()