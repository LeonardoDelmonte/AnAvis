import React from 'react';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import "react-datepicker/dist/react-datepicker.css";

function FormDatePicker(props){
    return (
        <div className="form-group">
            <label>{props.label}</label>
            <DatePicker
                onChange={props.onChange}
                selected={props.selected}
                selectsStart={props.selectsStart}
                selectsEnd={props.selectsEnd}
                showTimeSelect={props.showTimeSelect}
                showTimeSelectOnly={props.showTimeSelectOnly}
                timeIntervals={props.timeIntervals}
                timeCaption={props.timeCaption}
                minTime={props.minTime}
                maxTime={props.maxTime}
                minDate={props.minDate}
                maxDate={props.maxDate}
                dateFormat={props.dateFormat}

                endDate={props.endDate}
                startDate={props.startDate}
            />
        </div>
    );
}
export default FormDatePicker