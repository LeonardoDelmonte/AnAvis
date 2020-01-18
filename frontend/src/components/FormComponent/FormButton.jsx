import React, { Component } from 'react';

//colorType

//primary       --> Blu
//secondary     --> Grigio
//success       --> Verde
//danger        --> Rosso
//warning       --> Giallo
//info          --> Celeste
//light         --> Bianco
//dark          --> Grigio

class FormInput extends Component {

    render() {

        return (
            <div className="form-group">
                <button type="this.props.type" className={"btn btn-block btn-" + this.props.colorType}>{this.props.value}</button> 
            </div>
        );
    }
}

export default FormInput