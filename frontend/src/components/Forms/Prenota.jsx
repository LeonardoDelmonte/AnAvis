import React, { Component } from "react";
//Components
import Input from "../FormComponents/Input";
import Select from "../FormComponents/Select";
import DatePicker from "../FormComponents/DatePicker";
import Button from "../FormComponents/Button";
//-----Helpers
import { isSede } from '../../utils/helpers'

class EmergenzaSangue extends Component {

  render() {
    return (
      <div>
        <form onSubmit={this.props.handleSubmit} id="PrenotaForm">
          {isSede() &&
            <div className="row m-3">
              <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <Input label="Seleziona un utente per prenotare una donazione" type="text" id="emailDonatore" name="emailDonatore" value={this.props.fields.emailDonatore} onChange={this.props.handleChange} placeholder="Email donatore" />
              </div>
            </div>
          }
          <div className="row m-3" >
            <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
              <Select label={"Seleziona una regione"} id="regione" name="regione" options={this.props.regioni} onChange={this.props.handleRegione} isSearchable isClearable />
            </div>
            {this.props.regione &&
              <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                <Select label={"Seleziona una Provincia"} id="provincia" name="provincia" options={this.props.province} onChange={this.props.handleProvincia} isSearchable isClearable />
              </div>
            }
            {this.props.provincia &&
              <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4">
                <Select label={"Seleziona un Comune"} id="comune" name="comune" options={this.props.comuni} onChange={this.props.handleComune} isSearchable isClearable />
              </div>
            }
          </div>
          <div className="row m-3">
            <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4" >
              <DatePicker
                label="Dalla data"
                selectsStart
                minDate={new Date()}
                maxDate={this.props.endDate}
                selected={ this.props.startDate}
                onChange={this.props.handleStartDate}
                startDate={ this.props.startDate}
                endDate={this.props.endDate}
                dateFormat="dd/MM/yyyy"
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4" >
              <DatePicker
                label="Alla data"
                selectsEnd
                minDate={ this.props.startDate}
                selected={this.props.endDate}
                onChange={this.props.handleEndDate}
                startDate={ this.props.startDate}
                endDate={this.props.endDate}
                dateFormat="dd/MM/yyyy"
              />
            </div>

            {this.props.startDate && this.props.endDate && this.props.comune &&
              <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4 align-self-end" >
                <Button type="submit" value="Cerca" colorType="primary" />
              </div>
            }
          </div>
        </form>
      </div>
    );
  }
}

export default EmergenzaSangue;
