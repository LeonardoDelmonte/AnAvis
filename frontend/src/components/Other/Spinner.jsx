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

function Spinner(props) {
    return (
        <div>
            {!props.ready &&
                <div>
                    <h5 className="text-center mt-5">{props.message}</h5>
                    <div className="d-flex justify-content-center loader">
                        <div className="spinner-grow text-primary" role="status">
                            <span className="sr-only">Loading...</span>
                        </div>
                    </div>
                </div>
            }
        </div>
    );
}

export default Spinner