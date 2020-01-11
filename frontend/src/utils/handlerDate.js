import axios from 'axios'

const API_URL = 'http://localhost:8080'

class LoginService{

    insert(dateDto) {
        var config = {
            headers: { 'Authorization': localStorage.getItem('Authorization'), 
                    'Content-Type': 'application/json' }
        };
        return axios.post(
            API_URL + '/handlerDate/insert',
            dateDto,
            config
        );
    }
}

export default new LoginService()