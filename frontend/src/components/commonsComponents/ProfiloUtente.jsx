import React, { Component } from "react"
import ProfiloService from "../../utils/ProfiloService"
import FormModulo from "../FormComponents/FormModulo"
import FormProfilo from "../FormComponents/FormProfilo"
import FormHistory from "../FormComponents/FormHistory"
import FormListPrenotazioni from '../FormComponents/FormListPrenotazioni'
import FormListEmergenze from '../FormComponents/FormListEmergenze'

class ProfiloUtente extends Component {
  constructor(props) {
    super(props);

    this.state = {      
      fields:{},
      aud: "",
      stringa: ""
    };
  }
  
  

  componentDidMount() {
    ProfiloService.loadProfilo()
      .then(response => {
        this.setState({ fields: response.data.utente });
        this.setState({ aud: response.data.utente.ruolo });
      })
      .catch(error => {
        console.log("nessuna risposta dal server");
      });
  }


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
              {this.state.fields.id && <FormProfilo value={this.state.fields}/>}
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
                {this.state.aud==="donatore" && "MODULO"}
                {this.state.aud==="sedeAvis" && "LISTA PRENOTAZIONI"}
                {this.state.aud==="centroTrasfusione" && "LISTA EMERGENZE"}
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
              {this.state.aud==="donatore" && this.state.fields.id && <FormModulo value={this.state.fields.modulo}/>}
              {this.state.aud==="sedeAvis" && this.state.fields.id && <FormListPrenotazioni value={this.state.fields}/>}
              {this.state.aud==="centroTrasfusione" && this.state.fields.id && <FormListEmergenze value={this.state.fields}/>}
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
