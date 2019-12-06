import axios from 'axios'

const API_URL = 'http://localhost:8080'

class RegisterService{

    registerUser(state){
        return axios.post(API_URL+'/user/reg',state);
    }
}

export default new RegisterService()