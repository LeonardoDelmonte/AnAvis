import React, { Component } from 'react';

class TopMenu extends Component {
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
                            <a className="nav-link" href="prenota">Prenota</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="InsertDate">Inserisci data</a>
                        </li>
                    </ul>
                </div>
            </nav>
        )
    }
}

export default TopMenu