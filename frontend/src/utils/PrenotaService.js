import axiosInstance from './interceptor'

class PrenotaService {

    async search(getDateDto) {
        return await axiosInstance.post('/prenotazione/ottieni-date-libere',getDateDto)
    }

    async prenotaPerDonatore(prenotazioneDto) {       
        return await axiosInstance.post('/sede/prenota-per-donatore',prenotazioneDto)
    }
    
    async prenota(idPrenotazione) {       
        return await axiosInstance.post('/donatore/prenota-data',idPrenotazione, {
            headers: { "Content-Type": "application/json" }})
    }

    async getRegioni() {
        return await axiosInstance.get('/prenotazione/ottieni-regioni')
    }

    async getProvince(regione) {
        return await axiosInstance.get('/prenotazione/ottieni-provincie/'+regione)
    }

    async getComuni(provincia) {
        return await axiosInstance.get('/prenotazione/ottieni-comuni/'+provincia)
    }

}

export default new PrenotaService()