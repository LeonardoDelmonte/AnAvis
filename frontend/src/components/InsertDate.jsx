import React, { Component } from 'react';
import handlerDate from "../utils/handlerDate"
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import "react-datepicker/dist/react-datepicker.css";

class InsertDate extends Component {

    constructor(props) {
        super(props)
        this.state = {
            date: new Date(),
            startTime: new Date(),
            endTime: new Date(),
        }

    }

    selectedDate = date => {
        this.setState({ date: date }, () => { console.log(date) })
    }

    setStartTime = date => {
        this.setState({ startTime: date }, () => { console.log(date) })
    }

    setEndTime = date => {
        this.setState({ endTime: date }, () => { console.log(date) })
    }

    insertDate = () =>{
        var dateDto = {
            "dataIniziale":this.state.startTime,
            "dataFinale":this.state.endTime
        }
        handlerDate.insert(dateDto)
            .then(response => {                                            
                    console.log(response)                    
                });             
    }

    render() {
        const date = this.state.date;
        const startTime = this.state.startTime;
        const endTime = this.state.endTime;

        return (
            <div>
                <h1>Scegli data</h1>
                <div className="row m-3">
                    <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3" >
                        <label>Seleziona la data da inserire</label>
                        <DatePicker
                            selectsStart
                            selected={date}
                            onChange={date => this.selectedDate(date)}
                            startDate={date}
                            minDate={new Date()}
                            dateFormat="dd/MM/yyyy"
                        />
                    </div>
                    <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3" >
                        <label>Ora di inizo</label>
                        <DatePicker
                            selected={startTime}
                            onChange={date => this.setStartTime(date)}
                            showTimeSelect
                            showTimeSelectOnly
                            timeIntervals={15}
                            timeCaption="Time"
                            dateFormat="h:mm aa"
                            minTime={new Date().setHours(0, 0, 0, 0)}
                            maxTime={endTime}
                            selectsStart
                            startDate={date}

                        />
                    </div>
                    <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3" >
                        <label>Ora di fine</label>
                        <DatePicker
                            selected={endTime}
                            onChange={date => this.setEndTime(date)}
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
                        <button type="button" className="btn btn-primary btn-block " onClick={() => this.insertDate()} >Inserisci</button>
                    </div>
                </div>


            </div>
        );
    }
}




export default InsertDate