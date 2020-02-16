import React, { Component } from 'react';
//Components
import Input from '../FormComponents/Input'
import Button from '../FormComponents/Button'
//Services
import Autenticazione from '../../utils/Autenticazione';
//Helpers
import { ShowSimpleAlert } from '../../utils/helpers'


class RegisterDonatore extends Component {

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

        var registerDto = { "donatore": this.state.fields }

        Autenticazione.registerDonatore(registerDto)
            .then((response) => {
                this.setState({ fields: { ruolo: 'donatore' } })
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
                <div className="container">
                    <h2>REGISTRA UN DONATORE</h2>
                    <form onSubmit={this.handleSubmit} id="RegisterForm">
                        <div className="row">
                            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12" >
                                <div>
                                    <Input label="Nome" type="text" id="nome" name="nome" value={this.state.fields.nome} onChange={this.handleChange} required />
                                    <Input label="Cognome" type="text" id="cognome" name="cognome" value={this.state.fields.cognome} onChange={this.handleChange} required />
                                    <Input label="Email" type="text" id="RegisterEmail" name="email" value={this.state.fields.email} onChange={this.handleChange} required />
                                    <Button type="submit" value="Registrati" colorType="primary" />
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        );
    }
}

export default RegisterDonatore