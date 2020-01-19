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

function FormAlert (props) {
    return (
        <div className={"alert alert-" + props.colorType} role="alert">
            {props.message}
        </div>
    );
}

export default FormAlert