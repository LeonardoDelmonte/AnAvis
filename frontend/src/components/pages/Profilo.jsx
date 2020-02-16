import React, { Component } from 'react';
//Components
import Profilo from '../Forms/Profilo';
import Modulo from '../Forms/Modulo';
//service
import {isDonatore} from '../../utils/helpers'

class ProfiloPage extends Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    render() {
        var ruolo = JSON.parse(localStorage.getItem('Ruolo'));

        return (
            <div>
                <div className="container">
                    <h1>PROFILO</h1>
                    <Profilo />
                    {isDonatore() &&
                        <Modulo />
                    }
                </div>
            </div>

        );
    }
}

export default ProfiloPage