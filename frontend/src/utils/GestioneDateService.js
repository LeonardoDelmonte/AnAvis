import axiosInstance from './interceptor'

class GestioneDateService {

    async getPrenotazioni(){
        return await axiosInstance.get('/gestione-date/prenotazioni')
    }

    async deleteDate(Prenotazione){
        return await axiosInstance.delete('/gestione-date/cancellazione',
                { data:Prenotazione } 
          )
    }

    async getPrenotazioniDonatore(){
        return await axiosInstance.get('/donatore/prenotazioni')
    }

    async deleteDateDonatore(Prenotazione){
        return await axiosInstance.delete('/donatore/cancellazione',
                { data:Prenotazione } 
          )
    }
}

export default new GestioneDateService()