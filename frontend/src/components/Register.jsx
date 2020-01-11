import React, { Component } from 'react';
import LoginService from '../utils/LoginService';
import base64 from "base-64";
import utf8 from "utf8";

class Register extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ruolo: '',
            email: '',
            password: '',
            rpassword: '',

            easyPw: false,
            errorPw: false
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

    handleSubmit(event) {
        event.preventDefault();

        this.setState({
            easyPw: false,
            errorPw: false
        })

        console.log(this.state.ruolo);
        console.log(this.state.email);
        console.log(this.state.password);
        console.log(this.state.rpassword);

        var registerDto = {
            'ruolo': this.state.ruolo,
            'email': this.state.email,
            'pw': base64.encode(utf8.encode(this.state.password))
        }

        const reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
        const test = reg.test(this.state.password);
        if (!test) {
            this.setState({easyPw: true})
            return;
        }
        if (this.state.password != this.state.rpassword) {
            this.setState({errorPw: true})
            return;
        }
        alert("BONOOOOOOOO")



        LoginService.register(registerDto)
            .then(
                response => {
                    console.log(response);
                }
            ).catch(err => {
                console.log(err);
            })

        
    }


    render() {

        return (
            <div>
                <h1>Registrazione</h1>
                { this.state.easyPw ?
                    <div className="alert alert-danger" role="alert">
                        La password deve essere composta da almeno 8 caratteri, una lettera minuscola, una lettera maiuscola, un numero e un carattere speciale
                    </div>
                : null }
                { this.state.errorPw ?
                    <div className="alert alert-danger" role="alert">
                        Le due password non corrispondono
                    </div>
                : null }
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label>Ruolo:</label>
                        <input type="text" className="form-control" id="ruolo" name="ruolo" value={this.state.ruolo} onChange={this.handleChange} required>
                        </input>
                    </div>
                    <div className="form-group">
                        <label>Email:</label>
                        <input type="text" className="form-control" id="email" name="email" value={this.state.email} onChange={this.handleChange} required>
                        </input>
                    </div>
                    <div className="form-group">
                        <label>Password:</label>
                        <input type="password" className="form-control" id="password" name="password" value={this.state.password} onChange={this.handleChange}>
                        </input>
                    </div>
                    <div className="form-group">
                        <label>Ripeti Password:</label>
                        <input type="password" className="form-control" id="rpassword" name="rpassword" value={this.state.rpassword} onChange={this.handleChange}>
                        </input>
                    </div>
                    <button type="submit" className="btn btn-primary">Registrati</button>
                </form>
            </div>
        );
    }
}

export default Register