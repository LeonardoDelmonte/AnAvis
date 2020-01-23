import axiosInstance from './interceptor'

class handlerDate{

    async insert(dateDto) {
        return await axiosInstance.post('/gestione-date/inserimento',dateDto)
    }

    async getDate(){
        return await axiosInstance.get('/gestione-date/prenotazioni')
    }
}

export default new handlerDate()