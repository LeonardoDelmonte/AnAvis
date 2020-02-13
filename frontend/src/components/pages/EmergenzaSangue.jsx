import React, { Component } from "react";
//Components
import FormEmergenzaSangue from '../Forms/EmergenzaSangue';
import TableEmergenzeSangue from '../Tables/EmergenzeSangue';
//Helpers
import { ShowSimpleAlert } from '../../utils/helpers'
//Services
import CentroTrasfusioneService from '../../utils/CentroTrasfusioneService';

class EmergenzaSangue extends Component {

    constructor(props) {
        super(props);
        this.TableEmergenzeSangueElement = React.createRef();
        this.state = {
            gruppoSanguigno: "0 Rh-"
        };
    }

    handleChangeSelect = data => {
        this.setState({
            gruppoSanguigno: data.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();

        CentroTrasfusioneService.inviaEmergenza(this.state.gruppoSanguigno)
            .then(response => {
                ShowSimpleAlert(response.data.message)
                this.TableEmergenzeSangueElement.current.getEmergency()
                this.setState({
                    gruppoSanguigno: "0 Rh-"
                });
                

            })
            .catch(error => {
                if (!error.response)
                    ShowSimpleAlert("Errore del server")
                else
                    ShowSimpleAlert(error.response.data.message)
            });
    };

    render() {

        return (
            <div>
                <div className="container">
                    <h1>EMERGENZA SANGUE</h1>
                    <h3>Compila il modulo per inserire una carenza di sangue</h3>
                    <FormEmergenzaSangue handleSubmit={this.handleSubmit} handleChangeSelect={this.handleChangeSelect} gruppoSanguigno={this.state.gruppoSanguigno}/>
                    <TableEmergenzeSangue ref={this.TableEmergenzeSangueElement}/>
                </div>
            </div>
        );
    }
}

export default EmergenzaSangue;
