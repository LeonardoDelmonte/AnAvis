import React, { Component } from 'react';
import ProfiloService from '../utils/ProfiloService';

class ProfiloUtente extends Component {

    constructor(props) {
        super(props)
        this.state = {
            res : null,  
            email: null,
            ruolo: null
        }
    }

    componentDidMount() {
        ProfiloService.loadProfilo()
           .then(
               response => {
                   this.setState({res : response.data})
                   this.setState({email: response.data.email})
                   this.setState({ruolo: response.data.ruolo})
               }
           )
    }


    modifica(){
        var res = {
            "donatore":this.state.res
        }
        ProfiloService.modificaProfilo(res)
    }

    render() {
        return (
            <div>
                <h1>PROFILO</h1>       
                    
                <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4 align-self-end" >
                    <input type="text" className="" value={this.state.ruolo}/> 
                    <input type="text" className="" value={this.state.email}/>
                    <button type="button" className="" onlick={this.modifica()} >Modifica</button>                                             
                </div>
                
            </div>
        );
    }
}


export default ProfiloUtente