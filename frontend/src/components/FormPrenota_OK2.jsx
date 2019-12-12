import React, { Component } from 'react';
import Select from 'react-select';
import { Formik, Form, Field, ErrorMessage } from 'formik';

import ListFreeDate from './ListFreeDate';
import PrenotaService from '../utils/PrenotaService';

class FormPrenota extends Component {

    constructor(props) {
        super(props)
        this.state = {
            regioni: [],
            regione: null
        }
    }

    setRegioni() {
        this.setState({ comuni: [] });
        PrenotaService.getRegioni()
            .then(
                response => {
                    response.data.map(
                        v =>
                            this.state.comuni.push({ value: v, label: v })
                    )
                }
            )
    }

    SelectedRegione = selectedOption => {
        this.setState({comune:selectedOption }, () => console.log(this.state.comune) )
    };
    
    componentDidMount() {
        this.setRegioni()
    }

    render() {
        var  comune  = this.state.comune;
        return (
            <div>
                <h4>Seleziona il comune</h4>
                <Select
                    value={comune}
                    onChange={this.SelectedRegione}
                    options={this.state.comuni}

                    placeholder = "Cerca"
                    isClearablea
                    noOptionsMessage = { () => "Nessun comune trovato"}
                    openMenuOnClick = {false}
                    openMenuOnFocus = {false}
                    components={{ DropdownIndicator:() => null, IndicatorSeparator:() => null }}
                />
            </div>
        );
    }
}



export default FormPrenota