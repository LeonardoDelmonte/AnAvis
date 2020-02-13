import React, { Component } from 'react';
//Services
import ProfiloService from '../../utils/ProfiloService';
//Components
import Istogramma from '../Graphics/Istogramma';
import News from '../Other/News';

class Dashboard extends Component {

    constructor(props) {
        super(props)
        this.state = {}
    }

    componentDidMount() {
        ProfiloService.loadProfilo()
            .then(response => {
                this.setState({ utente: response.data.utente });
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
    }

    render() {

        return (
            <div>
                <div className="container">
                    <h1>DASHBOARD</h1>
                    {this.state.utente && this.state.utente.ruolo === "donatore" && <h3>DONATORE {(this.state.utente.nome + " " + this.state.utente.cognome).toUpperCase()}</h3>}
                    {this.state.utente && this.state.utente.ruolo === "sedeAvis" && <h3>SEDE AVIS DI {(this.state.utente.comune).toUpperCase()}</h3>}
                    {this.state.utente && this.state.utente.ruolo === "centroTrasfusione" && <h3>CENTRO TRASFUSIONE DI  {(this.state.utente.comune).toUpperCase()}</h3>}
                    <Istogramma />
                    <News />
                </div>
            </div>

        );
    }
}

export default Dashboard