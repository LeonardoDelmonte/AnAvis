import React, {Component} from 'react';
import { BrowserRouter as Router, Route,Switch } from 'react-router-dom';
import './css/App.css';
import Home from './components/Home';
import FormPrenota from './components/FormPrenota';
import ListFreeDate from './components/ListFreeDate';

class App extends Component {
  render() {
    return (
      <Router>      
          <Switch>
              <Route path="/home" exact component={Home} />
              <Route path="/prenota" exact component={FormPrenota} />
              <Route path="/insertDate" exact component={ListFreeDate} />
          </Switch>
      </Router>
    );
  }
}

export default App;
