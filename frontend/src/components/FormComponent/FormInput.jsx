import React, { Component } from 'react';



class FormInput extends Component {

    render() {

        return (
            <div>
                <div className="form-group">
                    <label>{this.props.label}</label>
                    <input 
                        type={this.props.type} 
                        className="form-control" 
                        id={this.props.id} 
                        name={this.props.name} 
                        value={this.props.value} 
                        onChange={this.props.onChange} 
                        required={this.props.required ? true : ""} 
                    />
                </div>
            </div>
        );
    }
}

export default FormInput