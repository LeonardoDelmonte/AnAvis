import React, { Component } from 'react';
import LoginService from '../utils/LoginService';


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

        console.log(value);



        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        console.log(this.state.email);
        console.log(this.state.password);
        var loginDto = {
            'email': this.state.email,
            'pw': this.state.password
        }

        LoginService.login(loginDto)
            .then(
                response => {
                    console.log(response);
                    localStorage.removeItem('Authorization');
                    localStorage.setItem('Authorization', response.data);
                }
            ).catch(err => {
                console.log(err);
            })

        event.preventDefault();
    }


    render() {

        return (
            <div>
                <h1>Login</h1>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label>Email address:</label>
                        <input type="text" className="form-control" id="email" name="email" value={this.state.email} onChange={this.handleChange}>
                        </input>
                    </div>
                    <div className="form-group">
                        <label>Password:</label>
                        <input type="password" className="form-control" id="password" name="password" value={this.state.password} onChange={this.handleChange}>
                        </input>
                    </div>
                    <button type="submit" className="btn btn-primary">Login</button>
                </form>
            </div>
        );
    }
}

export default Login