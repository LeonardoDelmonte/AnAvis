import React, { Component } from "react";
import ProfiloService from "../../utils/ProfiloService";
import FormInput from "../FormComponents/FormInput";
import FormAlert from "../FormComponents/FormAlert";
import FormModulo from "../FormComponents/FormModulo";
import FormProfilo from "../FormComponents/FormProfilo";
import FormHistory from "../FormComponents/FormHistory";
import FormListPrenotazioni from '../FormComponents/FormListPrenotazioni'
import FormListEmergenze from '../FormComponents/FormListEmergenze'
import jwt from "jwt-decode";

class ProfiloUtente extends Component {
  constructor(props) {
    super(props);
    this.state = {
      aud: "",
      stringa: ""
    };
  }

  componentDidMount() {
    var ruolo = jwt(localStorage.getItem("Authorization")).aud;

    if (ruolo === "donatore")
      this.setState({ stringa: "MODULO" });
    else if (ruolo=== "sedeAvis")
      this.setState({ stringa: "LISTA PRENOTAZIONI" });
    else if (ruolo=== "centroTrasfusione")
      this.setState({ stringa: "LISTA EMERGENZE" });
    this.setState({aud:ruolo});
  }

  //non posso caricare lo state poi creare i componenti figli??? =(

  render() {
    return (
      <div id="accordion" className="mt-2">
        <div className="card">
          <div className="card-header" id="profilo">
            <h5 className="mb-0">
              <button
                className="btn btn-link collapsed"
                data-toggle="collapse"
                data-target="#collapseProfilo"
                aria-expanded="false"
                aria-controls="collapseProfilo"
              >
                PROFILO
              </button>
            </h5>
          </div>
          <div
            id="collapseProfilo"
            className="collapse show"
            aria-labelledby="profilo"
            data-parent="#accordion"
          >
            <div className="card-body">
              <FormProfilo />
            </div>
          </div>
        </div>
        <div className="card">
          <div className="card-header" id="modulo">
            <h5 className="mb-0">
              <button
                className="btn btn-link collapsed"
                data-toggle="collapse"
                data-target="#collapsemodulo"
                aria-expanded="false"
                aria-controls="collapsemodulo"
              >
                {this.state.stringa}
              </button>
            </h5>
          </div>
          <div
            id="collapsemodulo"
            className="collapse"
            aria-labelledby="modulo"
            data-parent="#accordion"
          >
            <div className="card-body">
              {this.state.aud==="donatore" && <FormModulo />}
              {this.state.aud==="sedeAvis" && <FormListPrenotazioni />}
              {this.state.aud==="centroTrasfusione" && <FormListEmergenze />}
            </div>
          </div>
        </div>
        <div className="card">
          <div className="card-header" id="history">
            <h5 className="mb-0">
              <button
                className="btn btn-link collapsed"
                data-toggle="collapse"
                data-target="#collapsehistory"
                aria-expanded="false"
                aria-controls="collapsehistory"
              >
                HISTORY
              </button>
            </h5>
          </div>
          <div
            id="collapsehistory"
            className="collapse"
            aria-labelledby="history"
            data-parent="#accordion"
          >
            <div className="card-body">
              <FormHistory />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default ProfiloUtente;
