import React, { Component } from 'react';
//Components
import FormSelect from './FormComponent/FormSelect';
import FormAlert from './FormComponent/FormAlert';
import FormButton from './FormComponent/FormButton';
//Services
import CentroTrasfusioneService from '../utils/CentroTrasfusioneService';

class EmergenzaSangue extends Component {

    constructor(props) {
        super(props);
        this.state = {
            gruppoSanguigno: '0 Rh-',
        };
    }

    handleChangeSelect = data => {
        this.setState({
            gruppoSanguigno: data.value
        })
    }

    handleChange = e => {
        const target = e.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit = e => {
        e.preventDefault();

        CentroTrasfusioneService.inviaEmergenza(this.state.gruppoSanguigno)
            .then(response => {
                document.getElementById("EmergencyForm").reset();
                this.setState({
                    alert: { message: response.data.message, type: "success" },
                    gruppoSanguigno: '0 Rh-',
                });
            }
            ).catch(error => {
                console.log(error)
                if (!error.response)
                    this.setState({ alert: { message: "Errore del server", type: "danger" } });
                else {
                    this.setState({ alert: { message: error.response.data.message, type: "danger" } });
                }
            }
            )
    }

    render() {
        const optionsGruppoSanguigno = [
            { value: '0 Rh-', label: '0 Rh-' },
            { value: '0 Rh+', label: '0 Rh+' },
            { value: 'A Rh-', label: 'A Rh-' },
            { value: 'A Rh+', label: 'A Rh+' },
            { value: 'B Rh-', label: 'B Rh-' },
            { value: 'B Rh+', label: 'B Rh+' },
            { value: 'AB Rh-', label: 'AB Rh-' },
            { value: 'AB Rh+', label: 'AB Rh+' }
        ]
        return (
            <div>
                <h1>Richiesta Sangue</h1>
                {this.state.alert &&
                    <FormAlert message={this.state.alert.message} colorType={this.state.alert.type} />
                }
                <form onSubmit={this.handleSubmit} id="EmergencyForm">
                    <div className="row">
                        <div className="col-sm-12 col-md-12 col-lg-10 col-xl-10 align-self-end" >
                            <FormSelect label="Seleziona gruppo sanguigno:" id="gruppoSanguigno" name="gruppoSanguigno" options={optionsGruppoSanguigno} value={this.state.gruppoSanguigno} onChange={this.handleChangeSelect} isSearchable={false} defaultValue={optionsGruppoSanguigno[0]} />
                        </div>
                        <div className="col-sm-4 col-md-4 col-lg-2 col-xl-2 align-self-end" >
                            <FormButton type="submit" value="Invia" colorType="primary"/>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default EmergenzaSangue