import React, { Component } from "react";
import ProfiloService from "../../utils/ProfiloService";
import FormInput from '../FormComponents/FormInput';
import FormAlert from '../FormComponents/FormAlert';

class FormProfilo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fields: {},
      isEnabled:false
    };
  }

  componentDidMount() {
    ProfiloService.loadProfilo()
      .then(response => {
        this.setState({ fields: response.data.utente });
      })
      .catch(error => {
        console.log("nessuna risposta dal server");
      });
  }

  handleChange = e => {
    const value = e.target.value;
    const name = e.target.name;

    this.setState(prevState => ({
      fields: {
        ...prevState.fields,
        [name]: value
      },
      isEnabled:true
    }));
  };

  handleSubmit = e => {
    e.preventDefault();
    var utente = { [this.state.fields.ruolo]: this.state.fields };
    console.log(utente);
    ProfiloService.updateProfilo(utente)
      .then(response => {
        this.setState({ message: response.data.message, type: "success" });
      })
      .catch(error => {
        this.setState({ message: error.response.data.message, type: "danger" });
      });
      this.setState({isEnabled:false})
  };

  render() {
    return (
      <div>
        <FormAlert message={this.state.message} colorType={this.state.type} />
        <form onSubmit={this.handleSubmit} id="ProfiloForm">
          <div className="row m-3">
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <FormInput
                label="Email"
                type="text"
                id="email"
                name="email"
                value={this.state.fields.email}
                onChange={this.handleChange}
                required
              />
            </div>
            {/*campi solo donatore */}
            {this.state.fields.ruolo === "donatore" && (
              <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <FormInput
                  label="Nome"
                  type="text"
                  id="nome"
                  name="nome"
                  value={this.state.fields.nome}
                  onChange={this.handleChange}
                  required
                />
                <FormInput
                  label="Cognome"
                  type="text"
                  id="cognome"
                  name="cognome"
                  value={this.state.fields.cognome}
                  onChange={this.handleChange}
                  required
                />
              </div>
            )}
            {/*campi SedeAvis e centroTrasfusione */}
            {(this.state.fields.ruolo === "sedeAvis" ||
              this.state.fields.ruolo === "centroTrasfusione") && (
              <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <FormInput
                  label="Denominazione"
                  type="text"
                  id="denominazione"
                  name="denominazione"
                  value={this.state.fields.denominazione}
                  onChange={this.handleChange}
                  required
                />
                <FormInput
                  label="Indirizzo"
                  type="text"
                  id="indirizzo"
                  name="indirizzo"
                  value={this.state.fields.indirizzo}
                  onChange={this.handleChange}
                  required
                />
                <FormInput
                  label="Regione"
                  type="text"
                  id="regione"
                  name="regione"
                  value={this.state.fields.regione}
                  onChange={this.handleChange}
                  required
                />
                <FormInput
                  label="Provincia"
                  type="text"
                  id="provincia"
                  name="provincia"
                  value={this.state.fields.provincia}
                  onChange={this.handleChange}
                  required
                />
                <FormInput
                  label="Comune"
                  type="text"
                  id="comune"
                  name="comune"
                  value={this.state.fields.comune}
                  onChange={this.handleChange}
                  required
                />
              </div>
            )}
            <div className="col-sm-12 col-md-12 col-lg-12 col-xl-12">
              <button
                type="submit"
                className="btn btn-primary btn-lg btn-block"
                disabled={!this.state.isEnabled}
              >
                modifica Profilo
              </button>
            </div>
          </div>
        </form>
      </div>
    );
  }
}

export default FormProfilo;
