import axiosInstance from './interceptor'

class CentroTrasfusioneService {

    async inviaEmergenza(gruppoSanguigno) {
        return await axiosInstance.post('/requestEmerg/insert',gruppoSanguigno)
    }
}

export default new CentroTrasfusioneService()