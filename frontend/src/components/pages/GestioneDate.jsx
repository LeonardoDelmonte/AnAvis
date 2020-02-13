import React, { Component } from 'react';
//Components
import GestioneDateSede from '../Tables/GestioneDateSede';
import GestioneDateDonatore from '../Tables/GestioneDateDonatore';

//-----Helpers
import { isSede, isDonatore } from '../../utils/helpers'

class GestioneDatePages extends Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    //-----Render
    render() {
        return (
            <div>
                <div className="container">
                    <h1>GESTIONE DATE</h1>
                    {isSede() &&
                        <div>
                            <h3>Qui puoi gestire le tue date libere e prenotate.</h3>
                            <GestioneDateSede />
                        </div>
                    }
                    {isDonatore() &&
                        <div>
                            <h3>Qui puoi visualizzare le tue prenotazioni</h3>
                            <GestioneDateDonatore />
                        </div>

                    }

                </div>
            </div>
        );
    }
}

export default GestioneDatePages