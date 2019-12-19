import React, { Component } from 'react';

import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import "react-datepicker/dist/react-datepicker.css";

class InsertDate extends Component {

    constructor(props) {
        super(props)
        this.state = {
            date: new Date(),
        }

    }

    selectedDate = date => {
        this.setState({ date: date }, () => { console.log(date) })
    }

    render() {
        const date = this.state.date;

        return (
            <div>
                <h1>Inserisci Date</h1>

                <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4" >
                    <label>Alla data</label>
                    <DatePicker
                        selectsStart
                        selected={date}
                        onChange={date => this.selectedDate(date)}
                        startDate={date}
                    />
                </div>
            </div>
        );
    }
}




export default InsertDate