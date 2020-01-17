import React, { Component } from 'react';
//Components
import FormInput from './FormComponent/FormInput';
import FormSelect from './FormComponent/FormSelect';
import FormAlert from './FormComponent/FormAlert';
//Services
import LoginService from '../utils/LoginService';

class Register extends Component {

    constructor(props) {
        super(props);
        this.state = {
            utente: {
                ruolo: 'donatore'
            },
        };
    }

    controllPassword(){
        const reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
        const test = reg.test(this.state.utente.password);
        if (!test) {
            this.setState({alert: { message: 'La password deve essere composta da almeno 8 caratteri, una lettera minuscola, una lettera maiuscola, un numero e un carattere speciale', type: "danger"} });
            return false;
        }
        if (this.state.utente.password !== this.state.utente.rpassword) {
            this.setState({alert: { message: 'Le due password non corrispondono', type: "danger"} });
            return false;
        }
        return true
    }

    handleChangeSelect = data => {
        document.getElementById("RegisterForm").reset();
        this.setState({
            utente: {
                ruolo: data.value
            }
        })
    }

    handleChange = event => {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState(prevState => ({
            utente: {
                ...prevState.utente,
                [name]: value
            }
        }));
    }

    handleSubmit = e => {
        e.preventDefault();
        window.scrollTo({ top: 0, left: 0, behavior: 'smooth' });

        this.setState({alert: { message:'', type: ''} });

        if(!this.controllPassword())
            return;

        var registerDto = { [this.state.utente.ruolo]: this.state.utente }

        LoginService.register(registerDto)
            .then((response) => {
                document.getElementById("RegisterForm").reset();
                this.setState({ alert: {message: response.data.message, type: "success" },utente: {ruolo: 'donatore'}});}
            ).catch(error => {
                if (!error.response)
                    this.setState({alert: { message:"Errore del server", type: "danger"} });
                else {
                    if (error.response.status === 500) 
                        this.setState({alert: { message: error.response.data.message, type: "danger"} });
                    else 
                        this.setState({alert: { message: error.response.data.message, type: "danger"} });
                }
            })
    }

    render() {
        const optionsRuoli = [
            { value: 'donatore', label: 'Donatore' },
            { value: 'sedeAvis', label: 'Sede avis' },
            { value: 'centroTrasfusione', label: 'Centro trasfusioni' }
        ]
        return (
            <div className="login-form">
                <h2 className="text-center"> Registrazione</h2>
                {this.state.alert &&
                    <FormAlert message={this.state.alert.message} type={this.state.alert.type} />
                }
                <form onSubmit={this.handleSubmit} id="RegisterForm">
                    <FormSelect label="Ruolo" id="ruolo" name="ruolo" options={optionsRuoli} value={this.state.utente.ruolo} onChange={this.handleChangeSelect} isSearchable={false} defaultValue={optionsRuoli[0]} />
                    {/*campi solo donatore */}
                    {
                        this.state.utente.ruolo === "donatore" &&
                        <div>
                            <FormInput label="Nome" type="text" className="form-control" id="nome" name="nome" value={this.state.email} onChange={this.handleChange} required="true" />
                            <FormInput label="Cognome" type="text" className="form-control" id="cognome" name="cognome" value={this.state.email} onChange={this.handleChange} required />
                        </div>
                    }
                    {/*campi SedeAvis e centroTrasfusione */}
                    {
                        (this.state.utente.ruolo === "sedeAvis" || this.state.utente.ruolo === "centroTrasfusione") &&
                        <div>
                            <FormInput label="Denominazione" type="text" className="form-control" id="denominazione" name="denominazione" value={this.state.email} onChange={this.handleChange} required />
                            <FormInput label="Indirizzo" type="text" className="form-control" id="indirizzo" name="indirizzo" value={this.state.email} onChange={this.handleChange} required />
                            <FormInput label="Regione" type="text" className="form-control" id="regione" name="regione" value={this.state.email} onChange={this.handleChange} required />
                            <FormInput label="Provincia" type="text" className="form-control" id="provincia" name="provincia" value={this.state.email} onChange={this.handleChange} required />
                            <FormInput label="Comune" type="text" className="form-control" id="comune" name="comune" value={this.state.email} onChange={this.handleChange} required />
                        </div>
                    }
                    <FormInput label="Email" type="text" id="email" name="email" value={this.state.email} onChange={this.handleChange} required />
                    <FormInput label="Password" type="password" id="password" name="password" value={this.state.password} onChange={this.handleChange} />
                    <FormInput label="Ripeti Password" type="password" className="form-control" id="rpassword" name="rpassword" value={this.state.rpassword} onChange={this.handleChange} />

                    <button type="submit" className="btn btn-primary btn-block">Registrati</button>
                </form>
                <p className="text-center"><a href="/login">Clicca qui se hai già un account</a></p>
            </div>
        );
    }
}

export default Register