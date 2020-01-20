import React, { Component } from "react";
import FormInput from "../FormComponents/FormInput";
import FormAlert from '../FormComponents/FormAlert'
import ProfiloService from "../../utils/ProfiloService";

class FormModulo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fields: {},
      isEnabled: false
    };
  }

  componentDidMount() {
    ProfiloService.loadProfilo()
      .then(response => {
        this.setState({ fields: response.data.utente.modulo });
      })
      .catch(error => {
        console.log("nessuna risposta dal server");
      });
  }

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
    ProfiloService.modificaModulo(this.state.fields)
      .then(res => {
        this.setState({ message: res.data.message, type: "success" });
      })
      .catch(err => {
        this.setState({ message: err.response.data.message, type: "danger" });
      });
      this.setState({isEnabled:false})
  };


  render() {
    return (
      <div>
      <FormAlert message={this.state.message} colorType={this.state.type} />
        <form id="ModuloForm" onSubmit={this.handlerSubmit}>
          <div className="row m-3">
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="GruppoSanguigno"
                type="text"
                id="gruppoSanguigno"
                name="gruppoSanguigno"
                value={this.state.fields.gruppoSanguigno}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Fumatore"
                type="text"
                id="fumatoreome"
                name="fumatore"
                value={this.state.fields.fumatore}
                onChange={this.handlerChange}
                required
              />
            </div>
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <button type="submit" className="btn btn-primary btn-block" disabled={!this.state.isEnabled}>
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
