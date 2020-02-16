import React, { Component } from 'react';
//Components
import Input from '../FormComponents/Input'
import Button from '../FormComponents/Button'
import Select from '../FormComponents/Select'
//Services
import Autenticazione from '../../utils/Autenticazione';
//Helpers
import { ShowSimpleAlert, controllPassword } from '../../utils/helpers'

const optionsRuoli = [
    { value: 'donatore', label: 'Donatore' },
    { value: 'sedeAvis', label: 'Sede avis' },
    { value: 'centroTrasfusione', label: 'Centro trasfusioni' }
]

class Register extends Component {

    constructor(props) {
        super(props)
        this.state = {
            fields: {
                ruolo: 'donatore'
            }
        }
    }

    //-----Metodi per register

    handleChangeSelect = data => {
        document.getElementById("RegisterForm").reset();
        this.setState({
            fields: {
                ruolo: data.value
            }
        })
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

    handleSubmit = event => {
        event.preventDefault();

        if (!controllPassword(this.state.fields.password, this.state.fields.rpassword))
            return;

        var registerDto = { [this.state.fields.ruolo]: this.state.fields }

        Autenticazione.register(registerDto)
            .then((response) => {
                this.setState({fields:{ruolo:'donatore'}})
                ShowSimpleAlert(response.data.message)
            }
            ).catch(error => {
                if (!error.response) {
                    ShowSimpleAlert('Errore del server, contattare l\'amministratore. ')
                }
                else {
                    ShowSimpleAlert(error.response.data.message)
                }
            })
    }

    render() {
        return (
            <div>
                <h2>REGISTRATI</h2>
                <form onSubmit={this.handleSubmit} id="RegisterForm">
                    <div className="row">


                        <div className="col-sm-12 col-md-12 col-lg-6 col-xl-6" >

                            <Select label="Ruolo" id="ruolo" name="ruolo" options={optionsRuoli} value={this.state.fields.ruolo} onChange={this.handleChangeSelect} isSearchable={false} />
                            {/*campi solo donatore */}
                            {
                                this.state.fields.ruolo === "donatore" &&
                                <div>
                                    <Input label="Nome" type="text" id="nome" name="nome" value={this.state.fields.nome} onChange={this.handleChange} required />
                                    <Input label="Cognome" type="text" id="cognome" name="cognome" value={this.state.fields.cognome} onChange={this.handleChange} required />
                                </div>
                            }
                            {/*campi SedeAvis e centroTrasfusione */}
                            {
                                (this.state.fields.ruolo === "sedeAvis") &&
                                <div>
                                    <Input label="Regione" type="text" id="regione" name="regione" value={this.state.fields.regione} onChange={this.handleChange} required />
                                    <Input label="Provincia" type="text" id="provincia" name="provincia" value={this.state.fields.provincia} onChange={this.handleChange} required />
                                    <Input label="Comune" type="text" id="comune" name="comune" value={this.state.fields.comune} onChange={this.handleChange} required />
                                </div>
                            }
                            {
                                (this.state.fields.ruolo === "centroTrasfusione") &&
                                <div>
                                    <Input label="Regione" type="text" id="regione" name="regione" value={this.state.fields.regione} onChange={this.handleChange} required />
                                    <Input label="Provincia" type="text" id="provincia" name="provincia" value={this.state.fields.provincia} onChange={this.handleChange} required />
                                    <Input label="Comune" type="text" id="comune" name="comune" value={this.state.fields.comune} onChange={this.handleChange} required />
                                </div>
                            }
                            {/*campi in comune */}
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-6 col-xl-6" >

                            <Input label="Email" type="text" id="RegisterEmail" name="email" value={this.state.fields.email} onChange={this.handleChange} required />
                            <Input label="Password" type="password" id="RegisterPassword" name="password" value={this.state.fields.password} onChange={this.handleChange} />
                            <Input label="Ripeti Password" type="password" id="rpassword" name="rpassword" value={this.state.fields.rpassword} onChange={this.handleChange} />
                            <Button type="submit" value="Registrati" colorType="primary" />
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default Register