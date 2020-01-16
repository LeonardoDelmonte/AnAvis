import React, { Component } from 'react';
import handlerDate from "../utils/handlerDate"
import DatePicker from "react-datepicker";
import ResultInserDate from './ResultInserDate';
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import "react-datepicker/dist/react-datepicker.css";


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
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();

        const Startdate = new Date(this.state.date.getFullYear(), this.state.date.getMonth(), this.state.date.getDate(), this.state.startTime.getHours(), this.state.startTime.getMinutes(), 0, 0);
        const EndDate = new Date(this.state.date.getFullYear(), this.state.date.getMonth(), this.state.date.getDate(), this.state.endTime.getHours(), this.state.endTime.getMinutes(), 0, 0);
        console.log(Startdate);
        console.log(EndDate);

        var dateDto = {
            "dataIniziale": Startdate,
            "dataFinale": EndDate
        }

        handlerDate.insert(dateDto)
            .then(response => {
                console.log(response.data)

                this.setState({
                    listError : response.data.listError,
                    listOK : response.data.listOK
                })
            });
    }

    selectedDate = date => {
        this.setState({ date: date })
    }

    setStartTime = date => {
        this.setState({ startTime: date })
    }

    setEndTime = date => {
        this.setState({ endTime: date })
    }

    componentDidMount() {

    }

    render() {
        const date = this.state.date;
        const startTime = this.state.startTime;
        const endTime = this.state.endTime;

        return (
            <div>
                <h1>Scegli data</h1>
                <form onSubmit={this.handleSubmit} id="RegisterForm">
                    <div className="row m-3">
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3 align-self-end" >
                            <label>Seleziona data</label>
                            <DatePicker
                                onChange={this.selectedDate}
                                selectsStart
                                selected={date}
                                startDate={date}
                                minDate={new Date()}
                                dateFormat="dd/MM/yyyy"
                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3 align-self-end" >
                            <label>Ora di inizo</label>
                            <DatePicker
                                onChange={this.setStartTime}
                                selected={startTime}
                                showTimeSelect
                                showTimeSelectOnly
                                timeIntervals={15}
                                timeCaption="Time"
                                dateFormat="h:mm aa"
                                minTime={new Date().setHours(0, 0, 0, 0)}
                                maxTime={endTime}
                                selectsStart
                                startDate={date}
                                disabledKeyboardNavigation

                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3  align-self-end" >
                            <label>Ora di fine</label>
                            <DatePicker
                                onChange={this.setEndTime}
                                selected={endTime}
                                showTimeSelect
                                showTimeSelectOnly
                                timeIntervals={15}
                                timeCaption="Time"
                                dateFormat="h:mm aa"
                                minTime={startTime}
                                maxTime={new Date().setHours(23, 45, 0, 0)}
                                selectsEnd
                                endDate={date}
                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3 align-self-end" >
                            <button type="submit" className="mt-3 btn btn-primary btn-block" >Inserisci</button>
                        </div>
                    </div>
                </form>
                
                <ResultInserDate listError={this.state.listError} listOK={this.state.listOK}/>

            </div>
        );
    }
}




export default InsertDate