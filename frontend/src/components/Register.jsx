import React, { Component } from 'react';
import LoginService from '../utils/LoginService';
import base64 from "base-64";
import utf8 from "utf8";

class Register extends Component {

    constructor(props) {
        super(props);
        this.state = {

            utente: {
                ruolo: 'donatore'
            },

            errorRegister: '',
            registerOK: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        if(target.name === "ruolo"){
            document.getElementById("RegisterForm").reset();
            this.setState({
                utente:{
                    ruolo:value
                }
            })
        }else{
            this.setState(prevState => ({
                utente: {
                    ...prevState.utente,
                    [name]: value
                }
            }));
        }
    }

    handleSubmit(e) {
        e.preventDefault();

        this.setState({
            errorRegister: '',
            registerOK: ''
        })

        var registerDto = { [this.state.utente.ruolo] : this.state.utente }
        console.log(registerDto)

        const reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
        const test = reg.test(this.state.utente.password);
        if (!test) {
            this.setState({ errorRegister: 'La password deve essere composta da almeno 8 caratteri, una lettera minuscola, una lettera maiuscola, un numero e un carattere speciale' })
            return;
        }
        if (this.state.utente.password !== this.state.utente.rpassword) {
            this.setState({ errorRegister: 'Le due password non corrispondono' })
            return;
        }

        LoginService.register(registerDto)
            .then(() => {
                this.setState({ 
                    registerOK: 'Registrazione effettuata con successo, torna alla pagina di login per autenticarti',
                    utente: {
                        ruolo: 'donatore'
                    }
                });
            }
            ).catch(error => {
                if (!error.response) {
                    this.setState({ errorRegister: 'Errore del server, contattare l\'amministratore. ' })
                }
                else {
                    if (error.response.status === 500) {
                        this.setState({ errorRegister: 'Questo utente è già registrato' })
                    } else {
                        this.setState({ errorRegister: 'Si è verificato un errore.' })
                    }
                }
            })


    }

    render() {

        return (
            <div className="login-form">
                <h2 className="text-center"> Registrazione</h2>
                {this.state.errorRegister &&
                    <div className="alert alert-danger" role="alert">
                        {this.state.errorRegister}
                    </div>
                }
                {this.state.registerOK &&
                    <div className="alert alert-success" role="alert">
                        {this.state.registerOK}
                    </div>
                }

                <form onSubmit={this.handleSubmit} id="RegisterForm">
                    <div className="form-group">
                        <label>Ruolo:</label>
                        <select className="form-control" id="ruolo" name="ruolo" value={this.state.utente.ruolo} onChange={this.handleChange}>
                            <option value="donatore">Donatore</option>
                            <option value="sedeAvis">Sede avis</option>
                            <option value="centroTrasfusione">Centro trasfusioni</option>
                        </select>
                    </div>
                    {/*campi solo donatore */}
                    {
                        this.state.utente.ruolo === "donatore" &&
                        <div>
                            <div className="form-group">
                                <label>Nome:</label>
                                <input type="text" className="form-control" id="nome" name="nome" value={this.state.email} onChange={this.handleChange} required />
                            </div>
                            <div className="form-group">
                                <label>Cognome:</label>
                                <input type="text" className="form-control" id="cognome" name="cognome" value={this.state.email} onChange={this.handleChange} required />
                            </div>
                        </div>
                    }
                    {/*--------------------*/}
                    {/*campi SedeAvis e centroTrasfusione */}
                    {
                        (this.state.utente.ruolo === "sedeAvis" || this.state.utente.ruolo === "centroTrasfusione")  &&
                        <div>
                            <div className="form-group">
                                <label>Denominazione:</label>
                                <input type="text" className="form-control" id="denominazione" name="denominazione" value={this.state.email} onChange={this.handleChange} required />
                            </div>
                            <div className="form-group">
                                <label>Indirizzo:</label>
                                <input type="text" className="form-control" id="indirizzo" name="indirizzo" value={this.state.email} onChange={this.handleChange} required />
                            </div>
                            <div className="form-group">
                                <label>Regione:</label>
                                <input type="text" className="form-control" id="regione" name="regione" value={this.state.email} onChange={this.handleChange} required />
                            </div>
                            <div className="form-group">
                                <label>Provincia:</label>
                                <input type="text" className="form-control" id="provincia" name="provincia" value={this.state.email} onChange={this.handleChange} required />
                            </div>
                            <div className="form-group">
                                <label>Comune:</label>
                                <input type="text" className="form-control" id="comune" name="comune" value={this.state.email} onChange={this.handleChange} required />
                            </div>
                        </div>
                    }
                    {/*--------------------*/}
                    <div className="form-group">
                        <label>Email:</label>
                        <input type="text" className="form-control" id="email" name="email" value={this.state.email} onChange={this.handleChange} required />
                    </div>
                    <div className="form-group">
                        <label>Password:</label>
                        <input type="password" className="form-control" id="password" name="password" value={this.state.password} onChange={this.handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Ripeti Password:</label>
                        <input type="password" className="form-control" id="rpassword" name="rpassword" value={this.state.rpassword} onChange={this.handleChange} />
                    </div>
                    <button type="submit" className="btn btn-primary btn-block">Registrati</button>
                </form>
                <p className="text-center"><a href="/login">Clicca qui se hai già un account</a></p>
            </div>
        );
    }
}

export default Register