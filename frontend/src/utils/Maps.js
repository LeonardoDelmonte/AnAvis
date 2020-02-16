import axiosInstance from './interceptor'

class Maps{

    async ottieniComuni(){
        return await axiosInstance.get('/public/ottieni-comuni')
    }
    
}

export default new Maps()