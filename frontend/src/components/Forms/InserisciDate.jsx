import React, { Component } from "react";
//Components
import DatePicker from "../FormComponents/DatePicker";
import Button from "../FormComponents/Button";

class EmergenzaSangue extends Component {

    render() {
        const timeIntervals = 15
        const minDate = new Date().setHours(0, 0, 0, 0);
        const maxDate = new Date().setHours(23, 45, 0, 0);
        const date = this.props.date;
        const startTime = this.props.startTime;
        const endTime = this.props.endTime;
        return (
            <div>
                <form onSubmit={this.props.handleSubmit} id="RegisterForm">
                    <div className="row m-3">
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3 align-self-end" >
                            <DatePicker
                                label="Seleziona data"
                                onChange={this.props.handleDate}
                                selected={date}
                                minDate={minDate}
                                dateFormat="dd/MM/yyyy"
                            />
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-3 col-xl-3 align-self-end" >
                            <DatePicker
                                label="Ora di inizo"
                                onChange={this.props.handleStartTime}
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
                            <DatePicker
                                label="Ora di inizo"
                                onChange={this.props.handleEndDate}
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
                            <Button type="Inserisci" value="Inserisci" colorType="primary" />
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default EmergenzaSangue;