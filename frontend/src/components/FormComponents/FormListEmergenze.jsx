import React, { Component } from 'react'
import CentroTrasfusioneService from '../../utils/CentroTrasfusioneService'

class FormListEmergenze extends Component {
   constructor(props) {
        super(props)
    
        this.state = {
             listaEmergenze:[]    
            }
    }

    componentDidMount() {
        CentroTrasfusioneService.getEmergency().then(res=>{console.log(res);

            this.setState({listaLibere:res.data.listaEmergenze})
            
        }).catch(error=>{
            console.log(error)
        })
    }
    
    
    render() {
        return (
            <div>
                {console.log(this.state.listaEmergenze[0])}               
            </div>
        )
    }
}

export default FormListEmergenze
