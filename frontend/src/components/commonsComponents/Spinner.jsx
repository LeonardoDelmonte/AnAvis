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
                <div class="d-flex justify-content-center loader">
                    <div class="spinner-grow text-primary" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            }
        </div>

    );
}

export default Spinner