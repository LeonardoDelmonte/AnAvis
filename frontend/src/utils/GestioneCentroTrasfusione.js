import axiosInstance from './interceptor'

class GestioneCentroTrasfusione {

    async inviaEmergenza(gruppoSanguigno) {
        return await axiosInstance.post('/centro/inserisci-emergenze',gruppoSanguigno, {
            headers: { "Content-Type": "application/json" }})
    }

    async getEmergency(){
        return await axiosInstance.get('centro/ottieni-emergenze')
    }

    async deleteEmergenze(emergenza){
        return await axiosInstance.delete('centro/elimina-emergenze',
                { data:emergenza } 
          )
    }

    async GetCountRichieste(){
        return await axiosInstance.get('/vedi-richieste/conta-emergenze')
    }
    
}

export default new GestioneCentroTrasfusione()