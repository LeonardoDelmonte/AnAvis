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
        return await axiosInstance.get('/cancPrenotazioni/donatore/prenotazioni')
    }

    async deleteDateDonatore(Prenotazione){
        return await axiosInstance.delete('/cancPrenotazioni/donatore/cancellazione',
                { data:Prenotazione } 
          )
    }
}

export default new GestioneDateService()