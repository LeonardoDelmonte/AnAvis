import React, { Component } from 'react';
import Select from 'react-select';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import ListFreeDate from './ListFreeDate';
import PrenotaService from '../utils/PrenotaService';

class FormPrenota extends Component {

    constructor(props) {
        super(props)
        this.state = {
            regioni: [
                { value: 'morrovalle', label: 'morrovalle' },
                { value: 'puglia', label: 'Puglia' },
                { value: 'toscana', label: 'Toscana' },
            ],
            freeDate: [],
        }

    }



    handleChange = selectedOption => {
        if (selectedOption) {
            PrenotaService.search(selectedOption.value)
                .then(
                    response => {
                        this.setState({ freeDate: response.data })
                    }
                )

        }
    };



    render() {
        const { selectedOption } = this.state;

        return (
            <div>

                <Select
                    name="sede"
                    value={selectedOption}
                    onChange={this.handleChange}
                    options={this.state.regioni}
                    isClearable
                />

                <ListFreeDate freeDate={this.state.freeDate} />
            </div>

        );
    }
}



export default FormPrenota