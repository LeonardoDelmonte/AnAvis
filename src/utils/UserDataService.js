import axios from 'axios'

const API_URL = 'http://localhost:8080'

class UserDataService{

    retrieveAllUsers(){
        return axios.get(API_URL+'/admin/users');
    }
}

export default new UserDataService()