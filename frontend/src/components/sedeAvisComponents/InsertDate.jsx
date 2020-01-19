import React, { Component } from 'react';
//Components
import FormDatePicker from '../FormComponents/FormDatePicker';
import FormButton from '../FormComponents/FormButton';
import ResultInserDate from './ResultInserDate';
//Services
import handlerDate from "../../utils/handlerDate"


class InsertDate extends Component {

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

        handlerDate.insert(dateDto)
            .then(response => {
                this.setState({
                    listError: response.data.map.listError,
                    listOK: response.data.map.listOK
                })
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
        const timeIntervals = 15
        const minDate = new Date().setHours(0, 0, 0, 0);
        const maxDate = new Date().setHours(23, 45, 0, 0);
        const date = this.state.date;
        const startTime = this.state.startTime;
        const endTime = this.state.endTime;
        return (
            <div>
                <h1>Scegli data</h1>
                <form onSubmit={this.handleSubmit} id="RegisterForm">
                    <div className="row m-3">
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3 align-self-end" >
                            <FormDatePicker
                                label="Seleziona data"
                                onChange={this.handleDate}
                                selected={date}
                                minDate={minDate}
                                dateFormat="dd/MM/yyyy"
                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3 align-self-end" >
                            <FormDatePicker
                                label="Ora di inizo"
                                onChange={this.handleStartTime}
                                selected={startTime}
                                showTimeSelect
                                showTimeSelectOnly
                                timeIntervals={timeIntervals}
                                dateFormat="h:mm aa"
                                startDate={date}
                                minTime={minDate}
                                maxTime={endTime}   
                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3  align-self-end" >
                            <FormDatePicker
                                label="Ora di inizo"
                                onChange={this.handleEndDate}
                                selected={endTime}
                                showTimeSelect
                                showTimeSelectOnly
                                timeIntervals={timeIntervals}
                                dateFormat="h:mm aa"
                                endDate={date}
                                minTime={startTime}
                                maxTime={maxDate}
                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3 align-self-end" >
                            <FormButton type="Inserisci" value="Inserisci" colorType="primary"/>
                        </div>
                    </div>
                </form>
                <ResultInserDate listError={this.state.listError} listOK={this.state.listOK} />
            </div>
        );
    }
}




export default InsertDate