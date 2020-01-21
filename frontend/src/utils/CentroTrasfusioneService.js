import axiosInstance from './interceptor'

class CentroTrasfusioneService {

    async inviaEmergenza(gruppoSanguigno) {
        return await axiosInstance.post('/requestEmerg/insert',gruppoSanguigno)
    }

    async getEmergency(){
        return await axiosInstance.get('/requestEmerg/getEmergenze')
    }
}

export default new CentroTrasfusioneService()