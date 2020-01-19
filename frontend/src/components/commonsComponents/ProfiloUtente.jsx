import React, { Component } from 'react';
import ProfiloService from '../../utils/ProfiloService';
import FormInput from '../FormComponents/FormInput';
import FormSelect from '../FormComponents/FormSelect';
import FormAlert from '../FormComponents/FormAlert';
import Modulo from './Modulo';
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faClipboardList } from '@fortawesome/free-solid-svg-icons';
library.add(faClipboardList)



class ProfiloUtente extends Component {

    constructor(props) {
        super(props)
        this.state = {
            bool : false,
            fields : {

            }
        }   
    }

    componentDidMount() {
        ProfiloService.loadProfilo()
           .then(
               response => {
                   this.setState({fields : response.data.utente})              
               })
            .catch(error => {
                console.log("nessuna risposta dal server");
            })
           
    }

    handleChange = e =>{
       const value = e.target.value;
       const name = e.target.name;

       this.setState(prevState => ({
           fields: {
               ...prevState.fields,
               [name]: value
           }
       }));
   }

    handleSubmit = e => {
        e.preventDefault();
        var utente = { [this.state.fields.ruolo] : this.state.fields }
        console.log(utente);
        ProfiloService.updateProfilo(utente)
            .then(response=>{
                this.setState({message:response.data.message , type:"success"})
            }).catch(error => {
                this.setState({message:error.response.data.message , type:"danger"})
            })
    }
    
    handleModuleClick=e=>{
        this.setState({bool:true})
    }

    render() {
        return (
            
            <div>
                <h1>PROFILO</h1> 
                <FormAlert message={this.state.message} colorType={this.state.type} />
                    <form onSubmit={this.handleSubmit} id="ProfiloForm">
                    <div className="row m-3">
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                            <FormInput label="Nome" type="text" id="nome" name="nome" value={this.state.fields.nome} onChange={this.handleChange} required />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                            <FormInput label="Cognome" type="text" id="cognome" name="cognome" value={this.state.fields.cognome} onChange={this.handleChange} required />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                            <FormInput label="Email" type="text" id="email" name="email" value={this.state.fields.email} onChange={this.handleChange} required />                       
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                            <button type="submit" className="btn btn-primary btn-block">modifica Profilo</button>
                        </div>
                        <div className="col-sm-11 col-md-11 col-lg-3 col-xl-3">
                            <button type="button" className="btn btn-success btn-block" onClick={this.handleModuleClick}> 
                                <FontAwesomeIcon icon="clipboard-list" size="1x" className="glyphicon glyphicon-align-left icon-button"/>                           
                            Visualizza Modulo</button>
                        </div>
                        <div>
                        {this.state.bool && <Modulo value={this.state.fields}/>}
                        </div>
                    </div>
                    </form>

            </div>
        );
    }


}


export default ProfiloUtente