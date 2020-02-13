import React, { Component } from 'react';
//Components
import Input from '../FormComponents/Input'
import Button from '../FormComponents/Button'
import Select from '../FormComponents/Select'
//Services
import ProfiloService from '../../utils/ProfiloService';
//Helpers
import { ShowSimpleAlert, optionsGruppoSanguigno, optionsSiNo } from '../../utils/helpers'

class Modulo extends Component {

    constructor(props) {
        super(props)
        this.state = {
            fields: {
            },
        }
    }

    handleChange = event => {
        const value = event.target.value;
        const name = event.target.name;

        this.setState(prevState => ({
            fields: {
                ...prevState.fields,
                [name]: value
            }
        }));
    }

    handleChangeSelectGruppo = data => {
        this.setState(prevState => ({
            fields: {
                ...prevState.fields,
                "gruppoSanguigno": data.value
            }, isEnabled: true
        }));
    };

    handleChangeSelectFumatore = data => {
        this.setState(prevState => ({
            fields: {
                ...prevState.fields,
                "fumatore": data.value
            }, isEnabled: true
        }));
    };

    handlerSubmit = e => {
        e.preventDefault();

        var DtoModulo = {
            modulo: this.state.fields,
            email: this.state.email
        }

        ProfiloService.modificaModulo(DtoModulo)
            .then(response => {
                ShowSimpleAlert(response.data.message)
            })
            .catch(error => {
                ShowSimpleAlert(error.response.data.message)
            });
    };

    componentDidMount() {
        if (this.props.value)
            this.setState({ fields: this.props.value, email: this.props.email });
        else{
            ProfiloService.loadProfilo()
            .then(response => {
                this.setState({ 
                    fields: response.data.utente.modulo, 
                    email: response.data.utente.email });
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
        }
    }

    render() {
        return (
            <form id="ModuloForm" onSubmit={this.handlerSubmit}>
                <h3>MODULO</h3>
                <Input label="Indirizzo" type="text" id="indirizzo" name="indirizzo"
                    value={this.state.fields.indirizzo} onChange={this.handleChange} required />

                <Input label="Telefono" type="text" id="telefono" name="telefono"
                    value={this.state.fields.telefono} onChange={this.handleChange} required />

                <Input label="Codice Fiscale" type="text" id="codiceFiscale" name="codiceFiscale"
                    value={this.state.fields.codiceFiscale} onChange={this.handleChange} required />

                <Select label="Seleziona gruppo sanguigno" id="gruppoSanguigno" name="gruppoSanguigno"
                    options={optionsGruppoSanguigno} onChange={this.handleChangeSelectGruppo} isSearchable={false} value={this.state.fields.gruppoSanguigno} />

                <Select label="Fumatore" id="fumatore" name="fumatore"
                    options={optionsSiNo} onChange={this.handleChangeSelectFumatore} isSearchable={false} value={this.state.fields.fumatore} />

                <Input label="Malattie" type="text" id="malattie" name="malattie"
                    value={this.state.fields.malattie} onChange={this.handleChange} required />

                <Input label="Vaccinazioni" type="text" id="vaccinazioni" name="vaccinazioni"
                    value={this.state.fields.vaccinazioni} onChange={this.handleChange} required />

                <Input label="Allergie" type="text" id="allergie" name="allergie"
                    value={this.state.fields.allergie} onChange={this.handleChange} required />

                <Input label="Farmaci" type="text" id="farmaci" name="farmaci"
                    value={this.state.fields.farmaci} onChange={this.handleChange} required />

                <Button type="submit" value="Modifica Modulo" colorType="primary" />
            </form>

        );
    }
}

export default Modulo
