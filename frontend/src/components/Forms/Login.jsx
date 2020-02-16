import React, { Component } from 'react';
//Components
import Input from '../FormComponents/Input'
import Button from '../FormComponents/Button'
//Services
import Autenticazione from '../../utils/Autenticazione';

//Helpers
import { ShowSimpleAlert} from '../../utils/helpers'

class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fields: {
            },
        }
    }

    //-----Metodi per Login
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
            'password': this.state.fields.password
        }

        Autenticazione.login(loginDto)
            .then(
                (response) => {
                    localStorage.removeItem('Authorization');
                    localStorage.setItem('Authorization', response.data.token);
                    this.props.history.push('/gest/dashboard');
                }
            ).catch(error => {
                if (!error.response) {
                    ShowSimpleAlert('Errore del server, contattare l\'amministratore. ')
                }
                else {
                    console.log(error.response)
                    ShowSimpleAlert(error.response.data.message)
                }
            })
    }

    render() {
        return (
            <div>
                <h2>ENTRA</h2>
                <form onSubmit={this.handleSubmit}>
                    <Input label="Email" type="text" className="form-control" id="email" name="email" value={this.state.fields.email} onChange={this.handleChange} />
                    <Input label="Password" type="password" id="password" name="password" value={this.state.fields.password} onChange={this.handleChange} />
                    <Button type="submit" value="Invia" colorType="primary" />
                </form>
            </div >
        );
    }
}
export default Login