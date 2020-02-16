import React, { Component } from 'react';
//Components
import InserisciDate from '../Forms/InserisciDate';
import ResultInserDate from '../Other/ResultInserDate';

import GestioneSedeAvis from "../../utils/GestioneSedeAvis"


class InserisciDatePage extends Component {

    constructor(props) {
        super(props)

        const myDate = new Date()
        myDate.setMinutes(0)

        this.state = {
            date: myDate,
            startTime: myDate,
            endTime: myDate
        }
    }

    handleSubmit = e => {
        e.preventDefault();

        const Startdate = new Date(this.state.date.getFullYear(), this.state.date.getMonth(), this.state.date.getDate(), this.state.startTime.getHours(), this.state.startTime.getMinutes(), 0, 0);
        const EndDate = new Date(this.state.date.getFullYear(), this.state.date.getMonth(), this.state.date.getDate(), this.state.endTime.getHours(), this.state.endTime.getMinutes(), 0, 0);

        var dateDto = {
            "dataIniziale": Startdate,
            "dataFinale": EndDate
        }

        GestioneSedeAvis.insert(dateDto)
            .then(response => {
                console.log(response.data.entity.ListError)
                this.setState({
                    listError: response.data.entity.ListError,
                    listOK: response.data.entity.ListOK
                }, () => {console.log(this.state)})
            });
    }

    handleDate = date => {
        this.setState({ date: date })
    }

    handleStartTime = date => {
        this.setState({ startTime: date })
    }

    handleEndDate = date => {
        this.setState({ endTime: date })
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h1>INSERISCI DATE</h1>
                    <h3>Specifica la data, l'orario di inizio e di fine per inserire le tue date dispinibile.</h3>
                    <InserisciDate date={this.state.date} startTime={this.state.startTime} endTime={this.state.endTime}
                        handleDate={this.handleDate} handleEndDate={this.handleEndDate} handleStartTime={this.handleStartTime} handleSubmit={this.handleSubmit}
                    />
                    <ResultInserDate listError={this.state.listError} listOK={this.state.listOK} />
                </div>
            </div>
        );
    }
}




export default InserisciDatePage