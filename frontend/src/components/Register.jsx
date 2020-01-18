import React, { Component } from 'react';
//Components
import FormInput from './FormComponent/FormInput';
import FormSelect from './FormComponent/FormSelect';
import FormAlert from './FormComponent/FormAlert';
import FormButton from './FormComponent/FormButton';
//Services
import LoginService from '../utils/LoginService';

class Register extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fields: {
                ruolo: 'donatore'
            },
        };
    }

    controllPassword(){
        const reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
        const test = reg.test(this.state.fields.password);
        if (!test) {
            this.setState({alert: { message: 'La password deve essere composta da almeno 8 caratteri, una lettera minuscola, una lettera maiuscola, un numero e un carattere speciale', type: "danger"} });
            return false;
        }
        if (this.state.fields.password !== this.state.fields.rpassword) {
            this.setState({alert: { message: 'Le due password non corrispondono', type: "danger"} });
            return false;
        }
        return true
    }

    handleChangeSelect = data => {
        document.getElementById("RegisterForm").reset();
        this.setState({
            fields: {
                ruolo: data.value
            }
        })
    }

    handleChange = event => {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState(prevState => ({
            fields: {
                ...prevState.fields,
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

        var registerDto = { [this.state.fields.ruolo]: this.state.fields }

        LoginService.register(registerDto)
            .then((response) => {
                document.getElementById("RegisterForm").reset();
                this.setState({ alert: {message: response.data.message, type: "success" },fields: {ruolo: 'donatore'}});}
            ).catch(error => {
                if (!error.response)
                    this.setState({alert: { message:"Errore del server", type: "danger"} });
                else {
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
                    <FormAlert message={this.state.alert.message} colorType={this.state.alert.type} />
                }
                <form onSubmit={this.handleSubmit} id="RegisterForm">
                    <FormSelect label="Ruolo" id="ruolo" name="ruolo" options={optionsRuoli} value={this.state.fields.ruolo} onChange={this.handleChangeSelect} isSearchable={false} defaultValue={optionsRuoli[0]} />
                    {/*campi solo donatore */}
                    {
                        this.state.fields.ruolo === "donatore" &&
                        <div>
                            <FormInput label="Nome" type="text" id="nome" name="nome" value={this.state.fields.nome} onChange={this.handleChange} required />
                            <FormInput label="Cognome" type="text" id="cognome" name="cognome" value={this.state.fields.cognome} onChange={this.handleChange} required />
                        </div>
                    }
                    {/*campi SedeAvis e centroTrasfusione */}
                    {
                        (this.state.fields.ruolo === "sedeAvis" || this.state.fields.ruolo === "centroTrasfusione") &&
                        <div>
                            <FormInput label="Denominazione" type="text" id="denominazione" name="denominazione" value={this.state.fields.denominazione} onChange={this.handleChange} required />
                            <FormInput label="Indirizzo" type="text" id="indirizzo" name="indirizzo" value={this.state.fields.indirizzo} onChange={this.handleChange} required />
                            <FormInput label="Regione" type="text" id="regione" name="regione" value={this.state.fields.regione} onChange={this.handleChange} required />
                            <FormInput label="Provincia" type="text" id="provincia" name="provincia" value={this.state.fields.provincia} onChange={this.handleChange} required />
                            <FormInput label="Comune" type="text" id="comune" name="comune" value={this.state.fields.comune} onChange={this.handleChange} required />
                        </div>
                    }
                    <FormInput label="Email" type="text" id="email" name="email" value={this.state.fields.email} onChange={this.handleChange} required />
                    <FormInput label="Password" type="password" id="password" name="password" value={this.state.fields.password} onChange={this.handleChange} />
                    <FormInput label="Ripeti Password" type="password" id="rpassword" name="rpassword" value={this.state.fields.rpassword} onChange={this.handleChange} />
                    <FormButton type="submit" value="Registrati" colorType="primary"/>
                </form>
                <p className="text-center"><a href="/login">Clicca qui se hai già un account</a></p>
            </div>
        );
    }
}

export default Register