import axiosInstance from './interceptor'

class CentroTrasfusioneService {

    async inviaEmergenza(gruppoSanguigno) {
        return await axiosInstance.post('/requestEmerg/insert',gruppoSanguigno)
    }

    async getEmergency(){
        return await axiosInstance.get('/requestEmerg/getEmergenze')
    }

    async deleteEmergenze(emergenza){
        return await axiosInstance.delete('/requestEmerg/remove', 
                
                { data: { emergenza: emergenza } }
         
          )
    }
}

export default new CentroTrasfusioneService()