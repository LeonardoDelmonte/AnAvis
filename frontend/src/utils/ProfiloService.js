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

}

export default new ProfiloService()