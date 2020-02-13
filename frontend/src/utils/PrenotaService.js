import axiosInstance from './interceptor'

class PrenotaService {

    async search(getDateDto) {
        return await axiosInstance.post('/prenotazione/date-libere',getDateDto)
    }

    async prenota(prenotazioneDto) {       
        return await axiosInstance.put('/prenotazione',prenotazioneDto)
    }

    async getRegioni() {
        return await axiosInstance.get('/prenotazione/regioni')
    }

    async getProvince(regione) {
        return await axiosInstance.get('/prenotazione/province/'+regione)
    }

    async getComuni(provincia) {
        return await axiosInstance.get('/prenotazione/comuni/'+provincia)
    }

    async insert(dateDto) {
        return await axiosInstance.post('/gestione-date/inserimento',dateDto)
    }

    async getDate(){
        return await axiosInstance.get('/gestione-date/prenotazioni')
    }
}

export default new PrenotaService()