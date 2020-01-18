import React, { Component } from 'react';

class Home extends Component {

    constructor(props) {
        super(props)
        this.state = {  }
    }

    componentDidMount(){
        this.setState({nome:localStorage.getItem('nome')})
        this.setState({cognome:localStorage.getItem('cognome')})
    }

    
    render() {
        return (
        <div>
            <div>
                <h1><b>Benvenuto </b>{this.state.nome} {this.state.cognome}</h1> 
            </div>
            <div>
    
            </div>
        </div>
        );
    }
}

export default Home