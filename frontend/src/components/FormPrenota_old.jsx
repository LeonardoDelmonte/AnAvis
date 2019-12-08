import React, { Component } from 'react';
import ListFreeDate from './ListFreeDate';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import PrenotaService from '../utils/PrenotaService';

class FormPrenota extends Component {

    constructor(props) {
        super(props)
        this.state = {
            sede: '',
            submitted: false,
            freeDate: []
        }
        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
    }

    onSubmit(values) {
        this.setState({submitted:true});
        let sede = values.sede;

        PrenotaService.search(sede)
           .then(
               response => {                  
                   this.setState({freeDate:response.data})
               }
           )
    }

    validate(values) {
        let errors = {}
        
        if (!values.sede) {
            errors.FormError = 'Enter a Description'
            console.log(errors.description)
        } else if (values.sede.length < 5) {
            errors.FormError = 'Enter atleast 5 Characters in Description'
            console.log(errors.description)
        }

        return errors
    }

    componentDidMount() {

       
    }

    render() {
        let { sede, FormError } = this.state
        return (
            <div>
                <Formik 
                    initialValues={{sede, FormError}}
                    onSubmit={this.onSubmit}
                    validateOnChange={false}
                    validateOnBlur={false}
                    validate={this.validate}
                    enableReinitialize={true}
                >
                    {
                        (props) => (
                            <Form>
                                <ErrorMessage name="FormError" component="div" className="alert alert-warning" />
                                <fieldset className="form-group">
                                    <label>Sede Avis</label>
                                    <Field className="form-control" type="text" name="sede" />
                                </fieldset>
                                <button className="btn btn-primary" type="submit">Cerca</button>
                                
                            </Form>
                        )
                    }
                </Formik>

                {this.state.submitted && <ListFreeDate freeDate={this.state.freeDate} />}
            </div>
        )
    }
}



export default FormPrenota