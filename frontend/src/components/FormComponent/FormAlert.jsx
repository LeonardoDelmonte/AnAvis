import React, { Component } from 'react';

//primary       --> Blu
//secondary     --> Grigio
//success       --> Verde
//danger        --> Rosso
//warning       --> Giallo
//info          --> Celeste
//light         --> Bianco
//dark          --> Grigio

class FormAlert extends Component {

    render() {

        return (
            <div className={"alert alert-" + this.props.type} role="alert">
                {this.props.message}
            </div>
        );
    }
}

export default FormAlert