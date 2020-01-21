import axiosInstance from './interceptor'

class handlerDate{

    async insert(dateDto) {
        return await axiosInstance.post('/handlerDate/insert',dateDto)
    }

    async getDate(){
        return await axiosInstance.get('/handlerDate/getPrenotazioni')
    }
}

export default new handlerDate()