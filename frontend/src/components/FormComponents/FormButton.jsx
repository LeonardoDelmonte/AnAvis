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

function FormInput(props) {
    return (
        <div className="form-group">
                    {/*this.props.type??????*/}
            <button type="this.props.type" className={"btn btn-block btn-" + props.colorType}>{props.value}</button> 
        </div>
    );
}

export default FormInput