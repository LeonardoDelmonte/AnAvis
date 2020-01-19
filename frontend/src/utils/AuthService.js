import axiosInstance from './interceptor'

class LoginService{

    async login(loginDto){
        var response = await axiosInstance.post('/public/login',loginDto)
            localStorage.removeItem('Authorization');
            localStorage.setItem('Authorization', response.data.token);
        return response;
    }

    async register(registerDto){
        return await axiosInstance.post('/public/register',registerDto,{ handlerEnabled: false })    
    }
}

export default new LoginService()