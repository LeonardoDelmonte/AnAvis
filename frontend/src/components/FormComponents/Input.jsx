import React from 'react';



function Input (props) {
    return (
        <div>
            <div className="form-group">
                <label>{props.label}</label>
                <input 
                    type={props.type} 
                    className="form-control" 
                    id={props.id} 
                    name={props.name} 
                    value={props.value || ""} 
                    onChange={props.onChange} 
                    required={props.required ? true : ""} 
                    placeholder={props.placeholder}                
                />
            </div>
        </div>
    );
}

export default Input