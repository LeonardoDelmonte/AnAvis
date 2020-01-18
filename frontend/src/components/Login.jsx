import React, { Component } from 'react';
//Components
import FormInput from './FormComponent/FormInput';
import FormAlert from './FormComponent/FormAlert';
import FormButton from './FormComponent/FormButton';
//Services
import LoginService from '../utils/LoginService';

class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fields: {
            },
        };
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

    handleSubmit = event => {
        event.preventDefault();

        this.setState({alert: { message:'', type: ''} });

        var loginDto = {
            'email': this.state.fields.email,
            'pw': this.state.fields.password
        }

        LoginService.login(loginDto)
            .then(
                response => {
                    console.log(response);
                    localStorage.removeItem('Authorization');
                    localStorage.setItem('Authorization', response.data.token);
                    localStorage.setItem('nome',response.data.utente.nome);
                     localStorage.setItem('cognome',response.data.utente.cognome);
                    this.props.history.push('/home')
                }
            ).catch(error => {
                if (!error.response) {
                    this.setState({ errLogin: 'Errore del server, contattare l\'amministratore. ' })
                }
                else {
                    this.setState({alert: { message: error.response.data.message, type: "danger"} });
                }
            })
    }

    render() {
        return (
            <div className="login-form">

                <h2 className="text-center"> Entra nel portale AnAvis</h2>
                {this.state.alert &&
                    <FormAlert message={this.state.alert.message} colorType={this.state.alert.type} />
                }
                <form onSubmit={this.handleSubmit}>
                    <FormInput label="Email" type="text" className="form-control" id="email" name="email" value={this.state.fields.email} onChange={this.handleChange} />
                    <FormInput label="Password" type="password" id="password" name="password" value={this.state.fields.password} onChange={this.handleChange} />
                    <FormButton type="submit" value="Invia" colorType="primary"/>
                </form>
                <p className="text-center"><a href="/register">Clicca qui se non sei ancora Registrato</a></p>

            </div >
        );
    }
}

export default Login