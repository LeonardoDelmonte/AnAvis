import React, { Component } from 'react';
import Select from 'react-select';

class FormSelect extends Component {
    render() {
        return (
            <div>
                <div className="form-group">
                    <label>{this.props.label}</label>
                    <Select
                        id={this.props.id}
                        name={this.props.name}
                        onChange={this.props.onChange}
                        options={this.props.options}
                        isSearchable={this.props.isSearchable}
                        placeholder="seleziona"
                        defaultValue={this.props.defaultValue}
                        isClearable={this.props.isClearable}
                        noOptionsMessage={() => "Nessun Risultato"}
                    />
                </div>
            </div>
        );
    }
}
export default FormSelect