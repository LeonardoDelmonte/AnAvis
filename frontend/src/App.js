import React, { Component } from 'react';
//-----router-----
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom';
//-----Helpers
import { isLogged, isDonatore, isSede, isCentro } from './utils/helpers'
//-----components-----
import TopMenu from './components/PersistentComponents/TopMenu';
import Footer from './components/PersistentComponents/Footer';

import Home from './components/pages/Home';
import Dashboard from './components/pages/Dashboard';
import Profilo from './components/pages/Profilo';
import Prenota from './components/pages/Prenota';
import InserisciDate from './components/pages/InserisciDate';
import GestioneDate from './components/pages/GestioneDate';
import Faq from './components/pages/Faq';

import EmergenzaSangue from './components/pages/EmergenzaSangue';


const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    isLogged() === true
      ? <Component {...props} />
      : <Redirect to='/' />
  )} />
)

const SedeRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    isSede()
      ? <Component {...props} />
      : <Redirect to='/' />
  )} />
)

const SedeDonatoreRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    (isSede() || isDonatore())
      ? <Component {...props} />
      : <Redirect to='/' />
  )} />
)

const CentroRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    isCentro()
      ? <Component {...props} />
      : <Redirect to='/' />
  )} />
)


class App extends Component {

  constructor(props) {
    super(props)
    this.state = {
    }
  }

  render() {
    return (

      <Router>
        <PrivateRoute path="/gest" component={(props) => <TopMenu  {...props} />} />

        <PrivateRoute path="/gest/dashboard" exact component={(props) => <Dashboard {...props} />} />
        <PrivateRoute path="/gest/profilo" exact component={(props) => <Profilo {...props} />} />

        <SedeDonatoreRoute path="/gest/prenota" exact component={(props) => <Prenota {...props} />} />

        <SedeRoute path="/gest/InserisciDate" exact component={(props) => <InserisciDate {...props} />} />
        <SedeDonatoreRoute path="/gest/GestioneDate" exact component={(props) => <GestioneDate {...props} />} />

        <CentroRoute path="/gest/emergenzaSangue" exact component={(props) => <EmergenzaSangue {...props} />} />

        <Route path="/gest/faq" exact render={(props) => <Faq {...props} />} />

        <Route path="/" exact render={(props) => <Home {...props} />} />

        <Route path="/" component={(props) => <Footer  {...props} />} />

      </Router>

    );
  }
}

export default App;
