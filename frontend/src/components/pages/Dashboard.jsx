import React, { Component } from 'react';
//Services
import ProfiloService from '../../utils/ProfiloService';
import {isDonatore, isCentro , isSede} from '../../utils/helpers';
//Components
import Istogramma from '../Graphics/Istogramma';
import News from '../Other/News';
import Maps from '../Other/Maps';

class Dashboard extends Component {

    constructor(props) {
        super(props)
        this.state = {}
    }

    componentDidMount() {
        if(isDonatore()){
            ProfiloService.loadProfiloDonatore()
            .then(response => {
                this.setState({ utente: response.data.entity });
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
        }
        if(isSede()){
            ProfiloService.loadProfiloSede()
            .then(response => {
                console.log(response.data)
                this.setState({ utente: response.data.entity });
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
        }
        if(isCentro()){
            ProfiloService.loadProfiloCentro()
            .then(response => {
                this.setState({ utente: response.data.entity });
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
        }
        
    }

    render() {

        return (
            <div>
                <div className="container">
                    <h1>DASHBOARD</h1>
                    {this.state.utente && isDonatore() && <h3>DONATORE {(this.state.utente.nome + " " + this.state.utente.cognome).toUpperCase()}</h3>}
                    {this.state.utente && isSede()     && <h3>SEDE AVIS DI {(this.state.utente.comune).toUpperCase()}</h3>}
                    {this.state.utente && isCentro() &&  <h3>CENTRO TRASFUSIONE DI  {(this.state.utente.comune).toUpperCase()}</h3>}
                    <Maps />
                    <Istogramma />
                    <News />
                </div>
            </div>

        );
    }
}

export default Dashboard