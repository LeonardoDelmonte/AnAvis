import React from 'react';

//colorType

//primary       --> Blu
//secondary     --> Grigio
//success       --> Verde
//danger        --> Rosso
//warning       --> Giallo
//info          --> Celeste
//light         --> Bianco
//dark          --> Grigio

function Button(props) {
    return (
        <div className="form-group">
            <button type="this.props.type" className={"btn btn-block btn-" + props.colorType}>{props.value}</button> 
        </div>
    );
}

export default Button