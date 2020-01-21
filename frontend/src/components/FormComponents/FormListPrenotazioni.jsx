import React, { Component } from 'react'
import handlerDate from '../../utils/handlerDate'
class FormListPrenotazioni extends Component {

    constructor(props) {
        super(props)
    
        this.state = {
             listaLibere:[],
             listaPrenotate:[]
        }
    }

    componentDidMount() {
        handlerDate.getDate().then(res=>{console.log(res);

            this.setState({listaLibere:res.data.map.listaLibere,
                            listaPrenotate:res.data.map.listaPrenotate})
            
        })
    }
    
    
    render() {
        return (
            <div>
                {console.log(this.state.listaLibere[0])}
                {console.log(this.state.listaPrenotate[0])}
               
            </div>
        )
    }
}

export default FormListPrenotazioni
