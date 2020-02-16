import axiosInstance from './interceptor'

class Autenticazione{

    async login(loginDto){
        return await axiosInstance.post('/public/autenticazione-utente',loginDto)
    }

    async register(registerDto){
        return await axiosInstance.post('/public/registrazione-utente',registerDto,{ handlerEnabled: false })    
    }

    async registerDonatore(registerDto){
        return await axiosInstance.post('/sede/registrazione-utente',registerDto,{ handlerEnabled: false })    
    }
}

export default new Autenticazione()