import React, { Component } from 'react';
//Components
import Areogramma from '../GraphicsComponents/Areogramma';
import Cartesiano from '../GraphicsComponents/Cartesiano';
import Istogramma from '../GraphicsComponents/Istogramma';
import Maps from '../commonsComponents/maps';


class Home extends Component {

    constructor(props) {
        super(props)
        this.state = {  }
    }
    
    render() {
        return (
            <div>
                <h1>Benvenuto</h1> 
                <Maps />
                <Istogramma />
                <Areogramma />
                <Cartesiano />
            </div>
        );
    }
}

export default Home