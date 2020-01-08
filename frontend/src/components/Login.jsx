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

                    // export function doLogin(username, password) { 
                    //     if (username && password) { 
                    //         return function (dispatch) { 
                    //             let url = 'http://localhost:8082/api/authentication?username=' + username + '&password=' + base64.encode(utf8.encode(password))          
                    //             axios.post(url).then((response) => { localStorage.removeItem("token")                  
                    //             localStorage.setItem("token", response.data.token); dispatch({ type: "LOGIN_SUCCESS", payload: response.data }) }).catch((err) => { 
                    //                 dispatch({ type: "LOGIN_FAILED", payload: err }) }) } } return { type: "LOGIN_EMPTY", payload: { message: "Empty username or password.", } } }
                }
            )

        event.preventDefault();
    }


    render() {

        return (
            <div>
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