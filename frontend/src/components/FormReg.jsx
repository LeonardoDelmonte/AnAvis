import React, {Component} from 'react';
import RegisterService from '../utils/RegisterService';

class FormReg extends Component{

    constructor(props) {
        super(props)
        this.state = {
           nome:'',
           cognome:'',
           email:'',
           password:''
        }      
    }

    changeNome(event){
        this.setState({nome:event.target.value});
    }

    changeCognome(event){
        this.setState({cognome:event.target.value});
    }

    changeEmail(event){
        this.setState({email:event.target.value});
    }

    changePassword(event){
        this.setState({password:event.target.value});
    }

    onSubmit(event){
        window.location.replace("/admin/users");
        event.preventDefault();
    }

    signup(event){
        RegisterService.registerUser(this.state);
    }



    render(){
        return(                              
    <form onSubmit={this.onSubmit.bind(this)}  class="pure-form pure-form-stacked">
        <fieldset className="form-group">
            <label>nome</label>
            <input className="form-control" type="text" onChange={this.changeNome.bind(this)} required />
        </fieldset>
        <fieldset className="form-group">
            <label>cognome</label>
            <input className="form-control" type="text" onChange={this.changeCognome.bind(this)} required />
        </fieldset>
        <fieldset className="form-group">
            <label>email</label>
            <input className="form-control" type="text" onChange={this.changeEmail.bind(this)} required />
        </fieldset>
        <fieldset className="form-group">
            <label>password</label>
            <input className="form-control" type="text" onChange={this.changePassword.bind(this)} required />
        </fieldset>
        <button className="btn btn-success" type="submit" onClick={this.signup.bind(this)}>SignUp!</button>
    </form>                                                         
        )
    }
}

export default FormReg