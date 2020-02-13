import React, { Component } from "react";
//Components
import Select from "../FormComponents/Select";
import Button from "../FormComponents/Button";
//-----Helpers
import {optionsGruppoSanguigno} from '../../utils/helpers'

class EmergenzaSangue extends Component {

  render() {
    return (
      <div>
        <form onSubmit={this.props.handleSubmit} id="EmergencyForm">
          <div className="row">
            <div className="col-sm-12 col-md-12 col-lg-10 col-xl-10 align-self-end">
              <Select
                label="Seleziona gruppo sanguigno:"
                id="gruppoSanguigno"
                name="gruppoSanguigno"
                options={optionsGruppoSanguigno}
                value={this.props.gruppoSanguigno}
                onChange={this.props.handleChangeSelect}
                isSearchable={false}
                defaultValue={optionsGruppoSanguigno[0]}
              />
            </div>
            <div className="col-sm-4 col-md-4 col-lg-2 col-xl-2 align-self-end">
              <Button type="submit" value="Invia" colorType="primary" />
            </div>
          </div>
        </form>
      </div>
    );
  }
}

export default EmergenzaSangue;
