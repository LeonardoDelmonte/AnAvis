import React, { Component } from 'react';
//Components
import FormInput from './FormComponent/FormInput';
import FormSelect from './FormComponent/FormSelect';
import FormButton from './FormComponent/FormButton';
import FormDatePicker from './FormComponent/FormDatePicker';
import ListFreeDate from './ListFreeDate';
//Services
import PrenotaService from '../utils/PrenotaService';
//other
import jwt from 'jwt-decode'

class FormPrenota extends Component {

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
            isSede: jwt(localStorage.getItem('Authorization')).aud === "sedeAvis",
            fields: {
            },
        }
    }

    handleChange = event => {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState(prevState => ({
            fields: {
                ...prevState.fields,
                [name]: value
            }
        }));
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
                response => {
                    if (!response.data.listPrenotazione) {
                        response.data = []
                    } else {
                        response.data.listPrenotazione.forEach (
                            (x) => {
                                const myDate = new Date(x.date);
                                delete x["date"];
                                x["day"] = myDate.getDate() + "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear();
                                x["time"] = myDate.getHours() + ":" + myDate.getMinutes();
                            }
                        )
                    }
                    this.setState({ freeDate: response.data.listPrenotazione })
                    this.setState({ searched: true })
                }
            )
    }

    handleRegione = regione => {
        this.setState({ regione: null, provincia: null, comune: null }, () => {
            if (regione) {
                this.setState({ provincie: [] });
                PrenotaService.getProvince(regione.value)
                    .then(
                        response => {
                            response.data.set.map(
                                v =>
                                    this.state.provincie.push({ value: v, label: v })
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
        const startDate = this.state.startDate;
        const endDate = this.state.endDate;
        return (
            <div>
                <h1>Prenota Donazione</h1>
                <form onSubmit={this.handleSubmit} id="PrenotaForm">
                    {this.state.isSede &&
                        <div className="row m-3">
                            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                <FormInput label="Seleziona un utente per prenotare una donazione" type="text" id="donatore" name="donatore" value={this.state.fields.donatore} onChange={this.handleChange} placeholder="Email donatore" />
                            </div>
                        </div>
                    }
                    <div className="row m-3" >
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                            <FormSelect label={"Seleziona una regione"} id="regione" name="regione" options={this.state.regioni} onChange={this.handleRegione} isSearchable isClearable />
                        </div>
                        {this.state.regione &&
                            <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                <FormSelect label={"Seleziona una Provincia"} id="provincia" name="provincia" options={this.state.provincie} onChange={this.handleProvincia} isSearchable isClearable />
                            </div>
                        }
                        {this.state.provincia &&
                            <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                <FormSelect label={"Seleziona un Comune"} id="comune" name="comune" options={this.state.comuni} onChange={this.handleComune} isSearchable isClearable />
                            </div>
                        }
                    </div>
                    <div className="row m-3">
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4" >
                            <FormDatePicker
                                label="Dalla data"
                                selectsStart
                                minDate={new Date()}
                                maxDate={endDate}
                                selected={startDate}
                                onChange={this.handleStartDate}
                                startDate={startDate}
                                endDate={endDate}
                                dateFormat="dd/MM/yyyy"
                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4" >
                            <FormDatePicker
                                label="Alla data"
                                selectsEnd
                                minDate={startDate}
                                selected={endDate}
                                onChange={this.handleEndDate}
                                startDate={startDate}
                                endDate={endDate}
                                dateFormat="dd/MM/yyyy"
                            />
                        </div>

                        {this.state.startDate && this.state.endDate && this.state.comune &&
                            <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4 align-self-end" >
                                <FormButton type="submit" value="Cerca" colorType="primary" />
                            </div>
                        }
                    </div>
                </form>
                {this.state.searched && <ListFreeDate freeDate={this.state.freeDate} donatore={this.state.fields.donatore} />}
            </div>
        );
    }
}

export default FormPrenota