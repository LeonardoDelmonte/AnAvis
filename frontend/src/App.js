import React, { Component } from 'react';

//-----bootstrap-----
// import $ from 'jquery';
// import Popper from 'popper.js';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap/dist/css/bootstrap.min.css';
//-----css-----
import './css/App.css';
//-----router-----
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom';
//-----JWT-----
import jwt from 'jwt-decode'
//-----components-----
import Login from './components/Login';
import Register from './components/Register';
import Home from './components/Home';
import FormPrenota from './components/FormPrenota';
import InsertDate from './components/InsertDate';
import TopMenu from './components/TopMenu';
import LogOut from './components/LogOut';
import ProfiloUtente from './components/ProfiloUtente';

const Auth = {
  isLogged() {
    if (localStorage.getItem('Authorization') && jwt(localStorage.getItem('Authorization')).exp > Date.now() / 1000 | 0) {
      return true
    }
    return false;
  },
  isDonatore() {
    if (this.isLogged() && jwt(localStorage.getItem('Authorization')).aud === "donatore") {
      return true
    }
    return false;
  },
  isSede() {
    if (this.isLogged() && jwt(localStorage.getItem('Authorization')).aud === "sedeAvis") {
      return true;
    }
    return false;
  }
}

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    Auth.isLogged() === true
      ? <Component {...props} />
      : <Redirect to='/Login' />
  )} />
)

const DonatoreRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    Auth.isDonatore() === true
      ? <Component {...props} />
      : <Redirect to='/Login' />
  )} />
)

const SedeRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    Auth.isSede() === true
      ? <Component {...props} />
      : <Redirect to='/Login' />
  )} />
)

class App extends Component {

  componentDidMount() {
    Auth.isDonatore();
  }
  render() {
    return (
      <Router>
        <div>

          <Route path="/" component={() => <TopMenu isLogged={Auth.isLogged()} isDonatore={Auth.isDonatore()} isSede={Auth.isSede()} />} />

          <div className="container">
            {/* Pagine non Protette */}
            <Route path="/" exact component={Login} />
            <Route path='/login' exact component={Login} />
            <Route path='/register' exact component={Register} />
            <Route path="/logOut" exact component={LogOut} />

            {/* Pagine Condivise */}
            <PrivateRoute path="/home" exact component={Home} />
            <PrivateRoute path="/prenota" exact component={FormPrenota} />
            <PrivateRoute path="/profilo" exact component={ProfiloUtente} />

            {/* Pagine Solo Donatore */}

            {/* Pagine Solo Sede */}
            <SedeRoute path="/insertDate" exact component={InsertDate} />

          </div>
        </div>
      </Router>
    );
  }
}

export default App;
