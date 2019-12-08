import React, { Component } from 'react';

//-----bootstrap-----
import $ from 'jquery';
import Popper from 'popper.js';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap/dist/css/bootstrap.min.css';
//-----css-----
import './css/App.css';
//-----router-----
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
//-----components-----
import Home from './components/Home';
import FormPrenota from './components/FormPrenota';
import ListFreeDate from './components/ListFreeDate';
import TopMenu from './components/TopMenu';

class App extends Component {
  render() {
    return (
      <>
        <TopMenu />
        <div className="container">

          <Router>
            <Switch>
              <Route path="/" exact component={Home} />
              <Route path="/home" exact component={Home} />
              <Route path="/prenota" exact component={FormPrenota} />
              <Route path="/insertDate" exact component={ListFreeDate} />
            </Switch>
          </Router>

        </div>
      </>
    );
  }
}

export default App;
