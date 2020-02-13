import React, { Component } from 'react';
//-----Helpers
import { isDonatore, isSede, isCentro } from '../../utils/helpers'

class TopMenu extends Component {

    constructor(props) {
        super(props);
        this.state = {
        }
    }

    logOut = () => {
        localStorage.removeItem("Authorization")
        localStorage.removeItem("Ruolo")
        this.props.history.push('/')
    }

    render() {
        return (

            <nav className="navbar navbar-expand-md navbar-dark" >
                <a className="navbar-brand" href="/gest/dashboard">AnAvis</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2" id="collapsibleNavbar">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            {(isDonatore() || isSede()) && <a className="nav-link" href="/gest/prenota">Prenota</a>}
                        </li>
                        <li className="nav-item">
                            {(isSede()) && <a className="nav-link" href="/gest/inserisciDate">Insersci Date</a>}
                        </li>
                        <li className="nav-item">
                            {(isDonatore() || isSede()) && <a className="nav-link" href="/gest/GestioneDate">Gestione Date</a>}
                        </li>
                        <li className="nav-item">
                            {isCentro() && <a className="nav-link" href="/gest/emergenzaSangue">Emergenza Sangue</a>}
                        </li>
                    </ul>
                    <ul className="navbar-nav ml-auto">

                        <li className="nav-item">
                            <a className="nav-link" href="/gest/profilo">Profilo</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/gest/faq">FAQ</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" onClick={this.logOut}>Esci</a>
                        </li>
                    </ul>
                </div>
            </nav>
        )
    }
}

export default TopMenu