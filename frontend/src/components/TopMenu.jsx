import React, { Component } from 'react';
import jwt from 'jwt-decode'

class TopMenu extends Component {

    // isLogged() {
    //     var isLogged = false;
    //     if (localStorage.getItem('Authorization') && jwt(localStorage.getItem('Authorization')).exp > Date.now() / 1000 | 0) {
    //       isLogged = true;
    //     }
    //     return isLogged;
    //   }
    
    //   componentDidMount() {
    //     if(!this.isLogged()){
    //       this.props.history.push('/login')
    //     }
    //   }

    render() {
        return (
            <nav className="navbar navbar-expand-md bg-dark navbar-dark">
                <a className="navbar-brand" href="/home">AnAvis</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="collapsibleNavbar">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <a className="nav-link" href="login">Login</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="register">Registrazione</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="prenota">Prenota</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="InsertDate">Inserisci data</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="LogOut">Esci</a>
                        </li>
                    </ul>
                </div>
            </nav>
        )
    }
}

export default TopMenu