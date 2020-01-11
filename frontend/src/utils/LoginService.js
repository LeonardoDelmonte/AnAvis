import axios from 'axios'

const API_URL = 'http://localhost:8080'

class LoginService{

    login(loginDto){
        return axios.post(API_URL+'/public/login',
        loginDto,
        {headers: {"Content-Type": "application/json"}
        });       
    }

    register(registerDto){
        return axios.post(API_URL+'/public/register',
        registerDto,
        {headers: {"Content-Type": "application/json"}
        });       
    }
}

export default new LoginService()