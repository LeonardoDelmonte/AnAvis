import React from 'react';

function TopMenu(props) {
    return (
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <a className="navbar-brand" href="/home">AnAvis</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2" id="collapsibleNavbar">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        {!props.isLogged && <a className="nav-link" href="login">Login</a>}
                    </li>
                    <li className="nav-item">
                        {!props.isLogged && <a className="nav-link" href="register">Registrazione</a>}
                    </li>
                    <li className="nav-item">
                        {props.isLogged && (props.isSede || props.isDonatore) && <a className="nav-link" href="prenota">Prenota</a>}
                    </li>
                    <li className="nav-item">
                        {props.isLogged && props.isSede && <a className="nav-link" href="InsertDate">Inserisci data</a>}
                    </li>
                    <li className="nav-item">
                        {props.isLogged && props.isCentro && <a className="nav-link" href="EmergenzaSangue">Emergenza sangue</a>}
                    </li>
                </ul>
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <a className="nav-link" href="news">News</a>

                    </li>
                    <li className="nav-item">
                        {props.isLogged && <a className="nav-link" href="profilo">Profilo</a>}

                    </li>
                    <li className="nav-item">
                        {props.isLogged && <a className="nav-link" href="LogOut">Esci</a>}
                    </li>
                </ul>
            </div>
        </nav>
    )
}

export default TopMenu