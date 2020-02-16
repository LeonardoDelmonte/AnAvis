import axiosInstance from './interceptor'

class GestioneDonatore{

    async ottieniDate(){
        return await axiosInstance.get('/donatore/gestione-date/ottieni-date')
    }

    async eliminaPrenotazione(prenotazione){
        return await axiosInstance.delete('/donatore/gestione-date/eliminare-data',{ data:prenotazione } )    
    }
    
}

export default new GestioneDonatore()