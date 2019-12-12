import React, { Component } from 'react';
import Select from 'react-select';
import { Formik, Form, Field, ErrorMessage } from 'formik';

import AsyncSelect from 'react-select/async';
import ListFreeDate from './ListFreeDate';
import PrenotaService from '../utils/PrenotaService';

class FormPrenota extends Component {

    constructor(props) {
        super(props)
        this.state = {
            regioni: [],
            provincie: [],
            freeDate: [],

            regione: '',
            provincia: '',
        }
        this.setComuni = this.setComuni.bind(this)
        this.setProvincie = this.setProvincie.bind(this)
        this.setComuni = this.setComuni.bind(this)
        this.setDate = this.setDate.bind(this)

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
        this.setState({ provincia: undefined })
        this.setState({ provincie: undefined })
        console.log(selectedRegione)
        if (selectedRegione) {
            this.setState({ regione: selectedRegione.value })
            this.setProvincie(selectedRegione.value)
        }
    };

    selectedProvincia = selectedProvincia => {
        if (selectedProvincia) {
            this.setState({ provincia: selectedProvincia.value })
            this.setComuni(selectedProvincia.value)
        }
    };

    componentDidMount() {
        this.setRegioni()
    }

    render() {
        const {selectedOption} = this.state;
        const {selectedOption2} = this.state;
        return (
            <div>
                <Select
                    value={selectedOption}
                    onChange={this.selectedRegione}
                    options={this.state.regioni}
                    isClearable
                />

                <Select
                    value={selectedOption2}
                    onChange={this.selectedProvincia}
                    options={this.state.provincie}
                    isClearable
                />
            </div>

        );
    }
}




export default FormPrenota