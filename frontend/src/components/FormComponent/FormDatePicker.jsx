import React, { Component } from 'react';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import "react-datepicker/dist/react-datepicker.css";

class FormDatePicker extends Component {
    render() {
        return (

            <div className="form-group">
                <label>{this.props.label}</label>
                <DatePicker
                    onChange={this.props.onChange}
                    selected={this.props.selected}

                    selectsStart={this.props.selectsStart}
                    selectsEnd={this.props.selectsEnd}

                    showTimeSelect={this.props.showTimeSelect}
                    showTimeSelectOnly={this.props.showTimeSelectOnly}
                    timeIntervals={this.props.timeIntervals}
                    timeCaption={this.props.timeCaption}
                    minTime={this.props.minTime}
                    maxTime={this.props.maxTime}

                    minDate={this.props.minDate}
                    maxDate={this.props.maxDate}
                    dateFormat={this.props.dateFormat}
    
                    endDate={this.props.endDate}
                    startDate={this.props.startDate}
                />
            </div>

        );
    }
}
export default FormDatePicker