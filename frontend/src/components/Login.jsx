import React, { Component } from 'react';
import LoginService from '../utils/LoginService';
import base64 from "base-64";
import utf8 from "utf8";


class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: ''
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

        var loginDto = {
            'email': this.state.email,
            'pw': base64.encode(utf8.encode(this.state.password))
        }

        LoginService.login(loginDto)
            .then(
                response => {
                    console.log(response);
                    localStorage.removeItem('Authorization');
                    localStorage.setItem('Authorization', response.data);
                    this.props.history.push('/home')
                }
            ).catch(err => {
                console.log(err);
            })
    }


    render() {

        return (
            <div className="login-form">

                    <h2 className="text-center"> Entra nel portale AnAvis</h2>
                    <form onSubmit={this.handleSubmit}>
                        <div className="form-group">
                            <label>Email:</label>
                            <input type="text" className="form-control" id="email" name="email" value={this.state.email} onChange={this.handleChange}>
                            </input>
                        </div>
                        <div className="form-group">
                            <label>Password:</label>
                            <input type="password" className="form-control" id="password" name="password" value={this.state.password} onChange={this.handleChange}>
                            </input>
                        </div>
                        <button type="submit" className="btn btn-primary btn-block">Accedi</button>
                    </form>
                    <p className="text-center"><a href="/register">Clicca qui se non sei ancora Registrato</a></p>

            </div >
        );
    }
}

export default Login