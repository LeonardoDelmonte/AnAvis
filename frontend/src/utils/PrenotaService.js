import axiosInstance from './interceptor'

class PrenotaService {

    async search(getDateDto) {
        return await axiosInstance.post('/prenotazione',getDateDto)
    }

    async prenota(prenotazioneDto) {       
        return await axiosInstance.put('/prenotazione/donatore',prenotazioneDto)
    }

    async getRegioni() {
        return await axiosInstance.get('/prenotazione/getRegioni')
    }

    async getProvince(regione) {
        return await axiosInstance.get('/prenotazione/getProvince/'+regione)
    }

    async getComuni(provincia) {
        return await axiosInstance.get('/prenotazione/getComuni/'+provincia)
    }
}

export default new PrenotaService()