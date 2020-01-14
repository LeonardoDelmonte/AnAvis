import React, { Component } from 'react';
import LoginService from '../utils/LoginService';
import base64 from "base-64";
import utf8 from "utf8";

class Register extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ruolo: 'donatore',
            email: '',
            password: '',
            rpassword: '',
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

        this.setState({
            [name]: value
        });

    }

    handleSubmit(e) {
        e.preventDefault();

        this.setState({
            errorRegister: '',
            registerOK: ''
        })

        

        var registerDto = JSON.stringify({
            "donatore" : {
                'ruolo': this.state.ruolo,
                'email': this.state.email,
                'pw': this.state.password,
                'nome' : 'leonardo',
                'cognome' : 'leonardo'
            }
        })

        const reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
        const test = reg.test(this.state.password);
        if (!test) {
            this.setState({ errorRegister: 'La password deve essere composta da almeno 8 caratteri, una lettera minuscola, una lettera maiuscola, un numero e un carattere speciale' })
            return;
        }
        if (this.state.password !== this.state.rpassword) {
            this.setState({ errorRegister: 'Le due password non corrispondono' })
            return;
        }

        LoginService.register(registerDto)
            .then(() => {
                this.setState({ registerOK: 'Registrazione effettuata con successo, torna alla pagina di login per autenticarti' });
                this.setState({
                    ruolo: 'donatore',
                    email: '',
                    password: '',
                    rpassword: '',
                })
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
                        <select className="form-control" id="ruolo" name="ruolo" value={this.state.ruolo} onChange={this.handleChange}>
                            <option value="donatore">Donatore</option>
                            <option value="sedeAvis">Sede avis</option>
                            <option value="centroTrasfusioni">Centro trasfusioni</option>
                        </select>
                    </div>
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