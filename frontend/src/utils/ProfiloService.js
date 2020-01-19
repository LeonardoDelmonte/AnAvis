import axiosInstance from './interceptor'

class ProfiloService {

    async loadProfilo() {
        return await axiosInstance.get('/profilo/showInfo')
    }

    async updateProfilo(utente){
        return await axiosInstance.put('/profilo/modificaCredenziali',utente)       
    }

}

export default new ProfiloService()