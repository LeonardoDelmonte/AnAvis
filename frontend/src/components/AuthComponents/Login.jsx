import React, { Component } from 'react';
//Components
import FormInput from '../FormComponents/FormInput';
import FormAlert from '../FormComponents/FormAlert';
import FormButton from '../FormComponents/FormButton';
//Services
import AuthService from '../../utils/AuthService';

class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fields: {
            },
        };
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

        var loginDto = {
            'email': this.state.fields.email,
            'pw': this.state.fields.password
        }

        AuthService.login(loginDto)
            .then(
                response => {
                    console.log("login success")
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