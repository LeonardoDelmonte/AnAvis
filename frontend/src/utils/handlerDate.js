import axiosInstance from './interceptor'

class handlerDate{

    async insert(dateDto) {
        return await axiosInstance.post('/handlerDate/insert',dateDto)
    }
}

export default new handlerDate()