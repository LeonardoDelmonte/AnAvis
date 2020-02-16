import axiosInstance from './interceptor'

class GestioneSedeAvis {

    async getPrenotazioni(){
        return await axiosInstance.get('/sede/gestione-date/ottieni-date')
    }

    async deleteDate(Prenotazione){
        return await axiosInstance.delete('/sede/gestione-date/elimina-data',
                { data:Prenotazione } 
          )
    }

    async insert(dateDto) {
        return await axiosInstance.post('/sede/gestione-date/inserisci-data',dateDto)
    }

    async modifica(dateDto) {
        return await axiosInstance.post('/sede/gestione-date/modifica-data',dateDto)
    }

}

export default new GestioneSedeAvis()