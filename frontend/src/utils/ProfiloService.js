import axiosInstance from './interceptor'

class ProfiloService {

    async loadProfilo() {
        return await axiosInstance.get('/profilo/info')
    }

    async loadModulo(email) {
        return await axiosInstance.get('/profilo/info-modulo/' + email)
    }

    async updateProfilo(utente){
        return await axiosInstance.put('/profilo/credenziali',utente)       
    }

    async modificaModulo(modulo){
        return await axiosInstance.put('/profilo/modulo',modulo)
    }

    async getPrenotazioni(){
        return await axiosInstance.get('/gestione-date/prenotazioni')
    }

    async deleteDate(Prenotazione){
        return await axiosInstance.delete('/gestione-date/cancellazione',
                { data:Prenotazione } 
          )
    }

}

export default new ProfiloService()