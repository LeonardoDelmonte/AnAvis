import React, { Component } from 'react';
//Services
import PrenotaService from '../../utils/PrenotaService';
//Components
import Prenota from '../Forms/Prenota';
import ListFreeDate from '../Tables/ListFreeDate';
//Helpers
import {dateToString, timeToString } from '../../utils/helpers'

class PrenotaPage extends Component {

    constructor(props) {
        super(props)
        const myDate1 = new Date()
        const myDate2 = new Date()
        myDate1.setHours(0, 0, 0, 0)
        myDate2.setDate(myDate2.getDate() + 7);
        myDate2.setHours(23, 59, 0, 0)

        this.state = {
            startDate: myDate1,
            endDate: myDate2,
            fields: {

            },
        }
    }

    handleChange = event => {
        const value = event.target.value;
        const name = event.target.name;

        this.setState(prevState => ({
            fields: {
                ...prevState.fields,
                [name]: value
            }
        }));
    }

    handleRegione = regione => {
        this.setState({ regione: null, provincia: null, comune: null }, () => {
            if (regione) {
                this.setState({ province: [] });
                PrenotaService.getProvince(regione.value)
                    .then(
                        response => {
                            response.data.set.map(
                                v =>
                                    this.state.province.push({ value: v, label: v })
                            )
                            this.setState({ regione: regione.value })
                        }
                    )
            }
        });
    };

    handleProvincia = provincia => {
        this.setState({ comune: null, provincia: null }, () => {
            if (provincia) {
                this.setState({ comuni: [] });
                PrenotaService.getComuni(provincia.value)
                    .then(
                        response => {
                            response.data.set.map(
                                v =>
                                    this.state.comuni.push({ value: v, label: v })
                            )
                            this.setState({ provincia: provincia.value })
                        }
                    )
            }
        });
    };
    
    handleComune = comune => {
        this.setState({ comune: null }, () => {
            if (comune) {
                this.setState({ comune: comune.value })
            }
        });
    };

    handleStartDate = date => {
        this.setState({ startDate: date })
    }

    handleEndDate = date => {
        this.setState({ endDate: date })
    }
    
    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({ freeDate: [] })
        var getDateDto = {
            "comune": this.state.comune,
            "dataIniziale": this.state.startDate,
            "dataFinale": this.state.endDate
        }
        PrenotaService.search(getDateDto)
            .then(
                response => {console.log(response)
                    if (!response.data.list) {
                        response.data = []
                    } else {
                        response.data.list.forEach (
                            (x) => {
                                const myDate = new Date(x.date);
                                delete x["date"];
                                x["day"] = dateToString(myDate)
                                x["time"] = timeToString(myDate)
                            }
                        )
                    }
                    this.setState({ freeDate: response.data.list, searched: true })
                }
            )
    }

    componentDidMount() {
        this.setState({ regioni: [] });
        PrenotaService.getRegioni()
            .then(
                response => {
                    response.data.set.map(
                        v =>
                            this.state.regioni.push({ value: v, label: v })
                    )
                }
            )
    }

    render() {

        return (
            <div>
                <div className="container">
                    <h1>PRENOTAZIONE DONAZIONE</h1>
                    <h3>Prenotati per effettuare una donazione</h3>
                    <Prenota    startDate={this.state.startDate} endDate={this.state.endDate} fields={this.state.fields}
                                regioni={this.state.regioni} province={this.state.province} comuni={this.state.comuni} 
                                regione={this.state.regione} provincia={this.state.provincia} comune={this.state.comune} 
                                handleProvincia={this.handleProvincia} handleRegione={this.handleRegione} handleComune={this.handleComune}
                                handleStartDate={this.handleStartDate} handleEndDate={this.handleEndDate} 
                                handleChange={this.handleChange} handleSubmit={this.handleSubmit}
                    />
                    {this.state.searched && 
                        <ListFreeDate freeDate={this.state.freeDate} emailDonatore={this.state.fields.emailDonatore} ruolo={this.state.ruolo} />
                    }
                    
                </div>
            </div>

        );
    }
}

export default PrenotaPage