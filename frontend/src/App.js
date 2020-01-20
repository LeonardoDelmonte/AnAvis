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
import Login from './components/AuthComponents/Login';
import Register from './components/AuthComponents/Register';
import Home from './components/commonsComponents/Home';
import FormPrenota from './components/commonsComponents/FormPrenota';
import InsertDate from './components/sedeAvisComponents/InsertDate';
import TopMenu from './components/PersistentComponents/TopMenu';
import LogOut from './components/AuthComponents/LogOut';
import ProfiloUtente from './components/commonsComponents/ProfiloUtente';
import Footer from './components/PersistentComponents/Footer';
import EmergenzaSangue from './components/CentroTrasfComponents/EmergenzaSangue';
import News from './components/NewsComponents/News';

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
  },
  isCentro() {
    if (this.isLogged() && jwt(localStorage.getItem('Authorization')).aud === "centroTrasfusione") {
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

// const DonatoreRoute = ({ component: Component, ...rest }) => (
//   <Route {...rest} render={(props) => (
//     Auth.isDonatore()
//       ? <Component {...props} />
//       : <Redirect to='/Login' />
//   )} />
// )

const SedeRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    Auth.isSede()
      ? <Component {...props} />
      : <Redirect to='/Login' />
  )} />
)

const SedeDonatoreRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    (Auth.isSede() || Auth.isDonatore())
      ? <Component {...props} />
      : <Redirect to='/Login' />
  )} />
)

const CentroRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    Auth.isCentro()
      ? <Component {...props} />
      : <Redirect to='/Login' />
  )} />
)

class App extends Component {

  render() {
    return (

      <Router>
        <Route path="/" component={() => <TopMenu isLogged={Auth.isLogged()} isDonatore={Auth.isDonatore()} isSede={Auth.isSede()} isCentro={Auth.isCentro()} />} />
      
          <div id="page-content">
            <div className="container">

              {/* Pagine non Protette */}
              <Route path="/" exact component={Login} />
              <Route path='/login' exact component={Login} />
              <Route path='/register' exact component={Register} />
              <Route path="/logOut" exact component={LogOut} />
              <Route path="/news" exact component={News} />

              {/* Pagine Condivise */}
              <PrivateRoute path="/home" exact component={Home} />
              <PrivateRoute path="/profilo" exact component={ProfiloUtente} />

              {/* Pagine Solo Donatore & Sede */}
              <SedeDonatoreRoute path="/prenota" exact component={FormPrenota} />

              {/* Pagine Solo Donatore */}

              {/* Pagine Solo Sede */}
              <SedeRoute path="/insertDate" exact component={InsertDate} />

              {/* Pagine Solo Centro */}
              <CentroRoute path="/EmergenzaSangue" exact component={EmergenzaSangue} />


            </div>
          </div>
    

        <Route path="/" component={() => <Footer />} />
      </Router>


    );
  }
}

export default App;
