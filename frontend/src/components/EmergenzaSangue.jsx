import React, { Component } from 'react';
import CentroTrasfusioneService from '../utils/CentroTrasfusioneService';


class EmergenzaSangue extends Component {

    constructor(props) {
        super(props);
        this.state = {
            gruppoSanguigno: '0 Rh-',
            invioOK: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });

    }

    handleSubmit(e) {
        e.preventDefault();
        console.log(this.state.gruppoSanguigno)

        CentroTrasfusioneService.inviaEmergenza(this.state.gruppoSanguigno)
            .then(() => {
                document.getElementById("EmergencyForm").reset();
                this.setState({ 
                    invioOK: 'Richiesta emergenza inviata con successo',
                    gruppoSanguigno: '0 Rh-',
                });
            }
            ).catch(error => {
                if (!error.response) {
                    this.setState({ 
                        errorRegister: 'Errore del server, contattare l\'amministratore. ',
                    })
                }
            })
    }

    render() {

        return (
            <div>
                <h1>Richiesta Sangue</h1>
                {this.state.invioOK &&
                    <div className="alert alert-success" role="alert">
                        {this.state.invioOK}
                    </div>
                }
                <form onSubmit={this.handleSubmit} id="EmergencyForm">
                    <div className="row m-3">
                        <div className="col-sm-12 col-md-12 col-lg-10 col-xl-10" >
                            <div className="form-group">
                                <label>Seleziona gruppo sanguigno:</label>
                                <select className="form-control" id="ruolo" name="gruppoSanguigno" value={this.state.gruppoSanguigno} onChange={this.handleChange}>
                                    <option value="0 Rh-">0 Rh -</option>
                                    <option value="0 Rh+">0 Rh +</option>
                                    <option value="A Rh-">A Rh -</option>
                                    <option value="A Rh+">A Rh +</option>
                                    <option value="B Rh-">B Rh -</option>
                                    <option value="B Rh+">B Rh +</option>
                                    <option value="AB Rh-">AB Rh -</option>
                                    <option value="AB Rh+">AB Rh +</option>
                                </select>
                            </div>
                        </div>
                        <div className="col-sm-4 col-md-4 col-lg-2 col-xl-2 align-self-end" >
                            <div className="form-group">
                                <button type="submit" className="btn btn-primary btn-block">Invia</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default EmergenzaSangue