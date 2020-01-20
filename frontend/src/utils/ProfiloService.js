import axiosInstance from './interceptor'

class ProfiloService {

    async loadProfilo() {
        return await axiosInstance.get('/profilo/showInfo')
    }

    async updateProfilo(utente){
        return await axiosInstance.put('/profilo/modificaCredenziali',utente)       
    }

    async modificaModulo(modulo){
        return await axiosInstance.put('/profilo/modificaModulo',modulo)
    }

}

export default new ProfiloService()