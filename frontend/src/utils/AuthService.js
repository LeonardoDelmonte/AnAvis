import axiosInstance from './interceptor'

class LoginService{

    async login(loginDto){
        return await axiosInstance.post('/public/login',loginDto)
    }

    async register(registerDto){
        return await axiosInstance.post('/public/register',registerDto,{ handlerEnabled: false })    
    }
}

export default new LoginService()