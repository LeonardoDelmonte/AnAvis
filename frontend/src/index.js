import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
//-----css
import './css/index.css';
import './css/App.css';
import 'react-confirm-alert/src/react-confirm-alert.css'
//-----bootstrap-----
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap/dist/css/bootstrap.min.css';
//----serviceWorker
import * as serviceWorker from './utils/serviceWorker';

//recupera il componente 'app' e lo renderizza nel div 'root' index.html
ReactDOM.render(<App/>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
