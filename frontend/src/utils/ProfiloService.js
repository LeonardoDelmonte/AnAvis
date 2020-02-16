import axiosInstance from './interceptor'

class ProfiloService {

    async updateProfiloDonatore(utente){
        return await axiosInstance.put('/donatore/gestione-profilo/modifica-profilo',utente)       
    }

    async updateProfiloSede(utente){
        return await axiosInstance.put('/sede/gestione-profilo/modifica-profilo',utente)       
    }

    async updateProfiloCentro(utente){
        return await axiosInstance.put('/centro/gestione-profilo/modifica-profilo',utente)       
    }

    async loadProfiloDonatore() {
        return await axiosInstance.get('/donatore/gestione-profilo/ottieni-profilo')
    }

    async loadProfiloSede() {
        return await axiosInstance.get('/sede/gestione-profilo/ottieni-profilo')
    }

    async loadProfiloCentro() {
        return await axiosInstance.get('/centro/gestione-profilo/ottieni-profilo')
    }

}

export default new ProfiloService()