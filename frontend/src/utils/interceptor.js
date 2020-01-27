import axios from 'axios'

//globals configure
const axiosInstance = axios.create({
    baseURL:'http://localhost:8080'
})

//activate interceptor or not
const isHandlerEnabled = (config={}) => {
  return config.hasOwnProperty('handlerEnabled') && !config.handlerEnabled ? 
    false : true
}

//request handler
const requestHandler = (request) => {
  if (isHandlerEnabled(request)) {
    // Modify request here
    if(request.method==="delete"){
      request.headers['data']=request.data
    }
      request.headers['Authorization'] = localStorage.getItem('Authorization')
  }
  return request
}

//enable Interceptors
axiosInstance.interceptors.request.use(
  request => requestHandler(request)
)

//response and errors handler

const errorHandler = (error) => {
  if (isHandlerEnabled(error.config)) {
    // Handle errors
  }
  return Promise.reject({ ...error })
}
const successHandler = (response) => {
  if (isHandlerEnabled(response.config)) {
    // Handle responses
  }
  return response
}
//enable Interceptors
axiosInstance.interceptors.response.use(
  response => successHandler(response),
  error => errorHandler(error)
)

export default axiosInstance;