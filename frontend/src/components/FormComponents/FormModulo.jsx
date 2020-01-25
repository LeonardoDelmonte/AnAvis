import React, { Component } from "react";
import FormInput from "../FormComponents/FormInput";
import FormAlert from "../FormComponents/FormAlert";
import FormSelect from '../FormComponents/FormSelect'
import ProfiloService from "../../utils/ProfiloService";

class FormModulo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fields: {
      },
      isEnabled: false
    };
  }

  componentDidMount() {
    if(this.props.value)
      this.setState({ fields: this.props.value, email: this.props.email });
  }

  handleChangeSelect = data => {
        this.setState(prevState =>({
          fields:{
            ...prevState.fields,
            ["gruppoSanguigno"]:data.value
          },isEnabled:true
         })); 
              console.log(this.state)            
    };

  handlerChange = e => {
    const value = e.target.value;
    const name = e.target.name;
    this.setState(prevState => ({
      fields: {
        ...prevState.fields,
        [name]: value
      },
      isEnabled: true
    }));
  };

  handlerSubmit = e => {
    e.preventDefault();
    var DtoModulo = {
      modulo: this.state.fields,
      email: this.state.email
    }
    console.log(DtoModulo);
    ProfiloService.modificaModulo(DtoModulo)
      .then(res => {
        this.setState({ message: res.data.message, type: "success"});
      })
      .catch(err => {
        this.setState({ message: err.response.data.message, type: "danger" });
      });
    this.setState({ isEnabled: false });
    console.log(this.state)
  };

  render() {
    const optionsGruppoSanguigno = [
      { value: '0 Rh-', label: '0 Rh-' },
      { value: '0 Rh+', label: '0 Rh+' },
      { value: 'A Rh-', label: 'A Rh-' },
      { value: 'A Rh+', label: 'A Rh+' },
      { value: 'B Rh-', label: 'B Rh-' },
      { value: 'B Rh+', label: 'B Rh+' },
      { value: 'AB Rh-', label: 'AB Rh-' },
      { value: 'AB Rh+', label: 'AB Rh+' }
    ]
    return ( 
      <div>
        <FormAlert message={this.state.message} colorType={this.state.type} />
        <form id="ModuloForm" onSubmit={this.handlerSubmit}>
          <div className="row m-3">
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormSelect
                label="Seleziona gruppo sanguigno:"
                id="gruppoSanguigno"
                name="gruppoSanguigno"
                options={optionsGruppoSanguigno}
                onChange={this.handleChangeSelect}
                isSearchable={false}
                value={this.state.fields.gruppoSanguigno}             
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Fumatore"
                type="text"
                id="fumatore"
                name="fumatore"
                value={this.state.fields.fumatore}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Codice Fiscale"
                type="text"
                id="codiceFiscale"
                name="codiceFiscale"
                value={this.state.fields.codiceFiscale}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Indirizzo"
                type="text"
                id="indirizzo"
                name="indirizzo"
                value={this.state.fields.indirizzo}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Telefono"
                type="text"
                id="telefono"
                name="telefono"
                value={this.state.fields.telefono}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Malattie"
                type="text"
                id="malattie"
                name="malattie"
                value={this.state.fields.malattie}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Vaccinazioni"
                type="text"
                id="vaccinazioni"
                name="vaccinazioni"
                value={this.state.fields.vaccinazioni}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Allergie"
                type="text"
                id="allergie"
                name="allergie"
                value={this.state.fields.allergie}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Farmaci"
                type="text"
                id="farmaci"
                name="farmaci"
                value={this.state.fields.farmaci}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <button
                type="submit"
                className="btn btn-primary btn-block"
                disabled={!this.state.isEnabled}
              >
                modifica Modulo
              </button>
            </div>
          </div>
        </form>
      </div>
    );
  }
}

export default FormModulo;
