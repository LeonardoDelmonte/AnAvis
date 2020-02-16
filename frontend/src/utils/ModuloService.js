import axiosInstance from './interceptor'

class ModuloService {

    async modificaModuloDonatore(modulo) {
        return await axiosInstance.put('/donatore/gestione-modulo/modifica-modulo', modulo)
    }

    async modificaModuloSede(moduloPerTerzi) {
        return await axiosInstance.put('/sede/modifica-modulo-donatore', moduloPerTerzi)
    }

    async loadModuloDonatore() {
        return await axiosInstance.get('/donatore/gestione-modulo/ottieni-modulo/')
    }
    
    async loadModuloSede(email) {
        return await axiosInstance.get('/sede/ottieni-modulo-donatore/' + email)
    }
}

export default new ModuloService()