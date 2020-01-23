import React from 'react';
import Select from 'react-select';

function FormSelect(props) {
    return (
        <div>
            <div className="form-group">
                <label>{props.label}</label>
                <Select
                    id={props.id}
                    name={props.name}
                    onChange={props.onChange}
                    options={props.options}
                    isSearchable={props.isSearchable}
                    placeholder="seleziona"
                    value={props.value && props.options.filter(option => option.value === props.value)}
                    defaultValue={props.defaultValue}
                    isClearable={props.isClearable}
                    noOptionsMessage={() => "Nessun Risultato"}
                />
            </div>
        </div>
    );
}
export default FormSelect