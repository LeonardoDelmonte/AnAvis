import axiosInstance from './interceptor'

class CentroTrasfusioneService {

    async inviaEmergenza(gruppoSanguigno) {
        return await axiosInstance.post('/emergezne/inserimento',gruppoSanguigno)
    }

    async getEmergency(){
        return await axiosInstance.get('/emergenze')
    }

    async deleteEmergenze(emergenza){
        return await axiosInstance.delete('/emergenze/cancellazione',
                { data:emergenza } 
          )
    }
}

export default new CentroTrasfusioneService()