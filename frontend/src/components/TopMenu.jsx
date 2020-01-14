import React, { Component } from 'react';


class TopMenu extends Component {


    render() {
        return (

            <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                <a className="navbar-brand" href="/home">AnAvis</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            {!this.props.isLogged && <a className="nav-link" href="login">Login</a>}
                        </li>
                        <li className="nav-item">
                            {!this.props.isLogged && <a className="nav-link" href="register">Registrazione</a>}
                        </li>
                        <li className="nav-item">
                            {this.props.isLogged && <a className="nav-link" href="prenota">Prenota</a>}
                        </li>
                        <li className="nav-item">
                            {this.props.isLogged && <a className="nav-link" href="profilo">Profilo</a>}
                        </li>
                        <li className="nav-item">
                            {this.props.isLogged && this.props.isSede && <a className="nav-link" href="InsertDate">Inserisci data</a>}
                        </li>
                    </ul>
                </div>
                <div className="navbar-collapse collapse w-100 order-3 dual-collapse2">
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item">
                            {this.props.isLogged && <a className="nav-link" href="LogOut">Esci</a>}
                        </li>
                    </ul>
                </div>
            </nav>
        )
    }
}

export default TopMenu