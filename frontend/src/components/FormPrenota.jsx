import React, { Component } from 'react';
import Select from 'react-select';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import ListFreeDate from './ListFreeDate';
import PrenotaService from '../utils/PrenotaService';
import { min } from 'date-fns';

class FormPrenota extends Component {

    constructor(props) {
        super(props)
        this.state = {
            regioni: [],
            provincie: [],
            comuni: [],

            regione: null,
            provincia: null,
            comune: null,

            startDate: null,
            endDate: null,

            freeDate: []
        }
        this.setComuni = this.setComuni.bind(this)
        this.setProvincie = this.setProvincie.bind(this)
        this.setComuni = this.setComuni.bind(this)
        this.setDate = this.setDate.bind(this)
        this.setStartDate = this.setStartDate.bind(this)
    }

    setRegioni() {
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

    setProvincie(regione) {
        this.setState({ provincie: [] });
        PrenotaService.getProvincie(regione)
            .then(
                response => {
                    response.data.map(
                        v =>
                            this.state.provincie.push({ value: v, label: v })
                    )
                }
            )
    }

    setComuni(provincia) {
        this.setState({ comuni: undefined });
        this.setState({ comuni: [] });
        PrenotaService.getComuni(provincia)
            .then(
                response => {
                    response.data.map(
                        v =>
                            this.state.comuni.push({ value: v, label: v })
                    )
                }
            )
    }

    setDate(comune) {
        PrenotaService.search(comune)
            .then(
                response => {
                    this.setState({ freeDate: response.data })
                }
            )
    }

    selectedRegione = selectedRegione => {
        this.setState({ regione: null, provincia: null, comune: null, startDate:null, endDate:null }, () => {
            if (selectedRegione) {
                this.setState({ regione: selectedRegione.value })
                this.setProvincie(selectedRegione.value)
            }
        });
    };

    selectedProvincia = selectedProvincia => {
        this.setState({ comune: null, provincia: null, startDate:null, endDate:null }, () => {
            if (selectedProvincia) {
                this.setState({ provincia: selectedProvincia.value })
                this.setComuni(selectedProvincia.value)
            }
        });
    };

    selectedcomune = selectedcomune => {
        this.setState({ comune: null, startDate:null, endDate:null }, () => {
            if (selectedcomune) {
                this.setDate(selectedcomune.value)
                this.setState({ comune: selectedcomune.value })
            }
        });
    };

    setStartDate(date) {
        this.setState({ startDate: date }, () => { console.log(date) })
    }

    setEndDate(date) {
        this.setState({ endDate: date }, () => { console.log(date) })
    }


    componentDidMount() {
        this.setRegioni()
    }

    render() {
        const startDate = this.state.startDate;
        const endDate = this.state.endDate;
        return (
            <div>
                <h1>Prenota Donazione</h1>
                <div className="row m-3" >
                    <SelectSearchDate dati={this.state.regioni} onSelected={this.selectedRegione} text={"Regione"} />
                    {this.state.regione && <SelectSearchDate dati={this.state.provincie} onSelected={this.selectedProvincia} text={"Provincia"} />}
                    {this.state.provincia && <SelectSearchDate dati={this.state.comuni} onSelected={this.selectedcomune} text={"Comune"} />}
                </div>
                {this.state.comune &&
                    <div className="row m-3">
                        <div className="col-sm-12 col-md-12 col-lg-6 col-xl-6 text-center" >
                            <label>Dalla data</label>
                            <DatePicker
                                inline
                                selectsStart
                                maxDate={endDate}
                                selected={startDate}
                                onChange={startDate => this.setStartDate(startDate)}
                                startDate={startDate}
                                endDate={endDate}
                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-6 col-xl-6 text-center" >
                            <label>Alla data</label>
                            <DatePicker
                                inline
                                selectsEnd
                                minDate={startDate}
                                selected={endDate}
                                onChange={endDate => this.setEndDate(endDate)}
                                startDate={startDate}
                                endDate={endDate}
                            />
                        </div>
                    </div>
                }


                {this.state.startDate && this.state.endDate && <ListFreeDate freeDate={this.state.freeDate} />}

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
                    placeholder="Cerca"
                    isClearablea
                    noOptionsMessage={() => "Nessun Risultato"}
                />
            </div>
        );
    }
}


export default FormPrenota