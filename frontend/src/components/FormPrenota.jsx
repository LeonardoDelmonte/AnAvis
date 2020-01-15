import React, { Component } from 'react';
import Select from 'react-select';

import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import "react-datepicker/dist/react-datepicker.css";
import ListFreeDate from './ListFreeDate';
import PrenotaService from '../utils/PrenotaService';
import jwt from 'jwt-decode'


class FormPrenota extends Component {

    constructor(props) {
        super(props)

        const myDate1 = new Date()
        const myDate2 = new Date()
        myDate1.setHours(0, 0, 0, 0)
        myDate2.setHours(23, 59, 0, 0)


        this.state = {
            regioni: [],
            provincie: [],
            comuni: [],

            regione: null,
            provincia: null,
            comune: null,

            startDate: myDate1,
            endDate: myDate2,

            searched: false,
            freeDate: [],

            isSede: jwt(localStorage.getItem('Authorization')).aud == "sedeAvis",
        }
        this.handlerDonatore = this.handlerDonatore.bind(this);
    }

    handlerDonatore(e){
        this.setState({
            donatore: e.target.value
        })
    }

    SearchFreeDate = (comune, dataInit, dataEnd) => {
        this.setState({ freeDate: [] })
        var getDateDto = {
            "comune": comune,
            "dataIniziale": dataInit,
            "dataFinale": dataEnd
        }
        PrenotaService.search(getDateDto)
            .then(
                response => {
                    if (!response.data) {
                        response.data = []
                    } else {
                        response.data.map(
                            (x) => {
                                const myDate = new Date(x.date);
                                delete x["date"];
                                x["day"] = myDate.getDate() + "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear();
                                x["time"] = myDate.getHours() + ":" + myDate.getMinutes();
                            }
                        )
                    }
                    this.setState({ freeDate: response.data })
                    this.setState({ searched: true })
                }
            )
    }

    selectedRegione = selectedRegione => {
        this.setState({ regione: null, provincia: null, comune: null }, () => {
            if (selectedRegione) {
                this.setState({ provincie: [] });
                PrenotaService.getProvince(selectedRegione.value)
                    .then(
                        response => {
                            response.data.map(
                                v =>
                                    this.state.provincie.push({ value: v, label: v })
                            )
                            console.log(response)
                            this.setState({ regione: selectedRegione.value })
                        }
                    )
            }
        });
    };

    selectedProvincia = selectedProvincia => {
        this.setState({ comune: null, provincia: null }, () => {
            if (selectedProvincia) {
                this.setState({ comuni: [] });
                PrenotaService.getComuni(selectedProvincia.value)
                    .then(
                        response => {
                            response.data.map(
                                v =>
                                    this.state.comuni.push({ value: v, label: v })
                            )
                            this.setState({ provincia: selectedProvincia.value })
                        }
                    )
            }
        });
    };

    selectedComune = selectedComune => {
        this.setState({ comune: null }, () => {
            if (selectedComune) {
                this.setState({ comune: selectedComune.value })
            }
        });
    };

    selectedStartDate = date => {
        this.setState({ startDate: date }, () => { console.log(date) })
    }

    selectedEndDate = date => {
        this.setState({ endDate: date }, () => { console.log(date) })
    }

    componentDidMount() {
        this.setState({ regioni: [] });
        PrenotaService.getRegioni()
            .then(
                response => {
                    response.data.map(
                        v =>
                            this.state.regioni.push({ value: v, label: v })
                    )
                }
            )
    }

    render() {
        const startDate = this.state.startDate;
        const endDate = this.state.endDate;
        return (
            <div>
                <h1>Prenota Donazione</h1>
                {this.state.isSede &&
                    <div className="row m-3">
                        <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12" >
                            <label>Seleziona un utente per prenotare una donazione</label>
                            <input className="form-control" type="text" name="donatore" id="donatore" onChange={this.handlerDonatore} placeholder="Email donatore"/>
                        </div>
                    </div>
                }

                <div className="row m-3" >
                    <SelectSearchDate dati={this.state.regioni} onSelected={this.selectedRegione} value={"Seleziona una regione"} text={"Regione"} />
                    {this.state.regione && <SelectSearchDate dati={this.state.provincie} onSelected={this.selectedProvincia} value={"Seleziona una Provincia"} text={"Provincia"}/>}
                    {this.state.provincia && <SelectSearchDate dati={this.state.comuni} onSelected={this.selectedComune} value={"Seleziona un Comune"} text={"Comune"}/>}
                </div>
                <div className="row m-3">
                    <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4" >
                        <label>Dalla data</label>
                        <DatePicker
                            selectsStart
                            maxDate={endDate}
                            selected={startDate}
                            onChange={startDate => this.selectedStartDate(startDate)}
                            startDate={startDate}
                            endDate={endDate}
                            dateFormat="dd/MM/yyyy"
                        />
                    </div>
                    <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4" >
                        <label>Alla data</label>
                        <DatePicker
                            selectsEnd
                            minDate={startDate}
                            selected={endDate}
                            onChange={endDate => this.selectedEndDate(endDate)}
                            startDate={startDate}
                            endDate={endDate}
                            dateFormat="dd/MM/yyyy"
                        />
                    </div>

                    {this.state.startDate && this.state.endDate && this.state.comune &&
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4 align-self-end" >
                            <button type="button" className="mt-3 btn btn-primary btn-block " onClick={() =>
                                this.SearchFreeDate(this.state.comune,
                                    this.state.startDate, this.state.endDate)}>Cerca</button>
                        </div>
                    }
                </div>
                {this.state.searched && <ListFreeDate freeDate={this.state.freeDate} donatore={this.state.donatore}/>}

            </div>
        );
    }
}


class SelectSearchDate extends Component {

    render() {
        return (
            <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                <label>{this.props.text}</label>
                <Select
                    onChange={this.props.onSelected}
                    options={this.props.dati}
                    isClearable
                    placeholder={this.props.value}
                    isClearablea
                    noOptionsMessage={() => "Nessun Risultato"}
                />
            </div>
        );
    }
}




export default FormPrenota