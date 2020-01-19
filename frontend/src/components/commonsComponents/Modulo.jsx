import React from 'react';
import FormInput from '../FormComponents/FormInput';


const Modulo = props =>  {
    return(
        <div>
        <h1>MODULO</h1>    
            <form id="ModuloForm">
                <div className="row m-3">
                    <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                    <FormInput label="GruppoSanguigno" type="text" id="gruppoSanguigno" name="gruppoSanguigno" value={props.value.modulo.gruppoSanguigno} required />
                    </div>
                    <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                    <FormInput label="Fumatore" type="text" id="fumatoreome" name="fumatore" value={props.value.modulo.fumatore}  required />
                    </div>
                    <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                    <button type="submit" className="btn btn-primary btn-block">modifica Modulo</button>
                    </div>                       
                </div>
            </form>
        </div>
        );
    }

export default Modulo