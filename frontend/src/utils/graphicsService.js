import axiosInstance from './interceptor'

class graphicsService{

    async GetDatiEmergenzeSangue() {
        return await axiosInstance.get('/countEmergenze')
    }
}

export default new graphicsService()