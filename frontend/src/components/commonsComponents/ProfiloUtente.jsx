import React, { Component } from "react"
import ProfiloService from "../../utils/ProfiloService"
import FormModulo from "../FormComponents/FormModulo"
import FormProfilo from "../FormComponents/FormProfilo"
import FormHistory from "../FormComponents/FormHistory"
import FormAlert from '../FormComponents/FormAlert'
import TableEmergenzeSangue from "../CentroTrasfComponents/TableEmergenzeSangue";
import CentroTrasfusioneService from "../../utils/CentroTrasfusioneService";
import TableDateLibere from "../sedeAvisComponents/TableDateLibere";
import TableDatePrenotate from "../sedeAvisComponents/TableDatePrenotate"
import TableDatePrenotateDonatore from "../sedeAvisComponents/TableDatePrenotateDonatore"




class ProfiloUtente extends Component {
  constructor(props) {
    super(props);

    this.state = {
      fields: {},
      aud: "",
      stringa: ""
    };
  }


  loadProfilo = () => {
    ProfiloService.loadProfilo()
      .then(response => {
        console.log(response)
        this.setState({ fields: response.data.utente });
        this.setState({ aud: response.data.utente.ruolo });
        if (this.state.aud === "donatore") this.getPrenotazioniDonatore()
        if (this.state.aud === "sedeAvis") this.getPrenotazioni()
        if (this.state.aud === "centroTrasfusione") this.getEmergency()
      })
      .catch(error => {
        console.log("nessuna risposta dal server");
      });
  }

  getEmergency = () => {
    CentroTrasfusioneService.getEmergency()
      .then(response => {
        response.data.list.forEach(
          (x) => {
            const myDate = new Date(x.dataEmergenza);
            x["dataEmergenza"] = myDate.getDate() + "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear();
          }
        )
        this.setState({
          emergenze: response.data.list
        });
      })
  }

  getPrenotazioni = () => {
    ProfiloService.getPrenotazioni()
      .then(response => {
        response.data.map.listaLibere.forEach(
          (x) => {
            const myDate = new Date(x.date);
            x["data"] = myDate.getDate() + "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear();
            x["time"] = myDate.getHours() + ":" + myDate.getMinutes();
          }
        )
        response.data.map.listaPrenotate.forEach(
          (x) => {
            const myDate = new Date(x.date);
            x["data"] = myDate.getDate() + "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear();
            x["time"] = myDate.getHours() + ":" + myDate.getMinutes();
          }
        )
        this.setState({
          listaPrenotate: response.data.map.listaPrenotate,
          listaLibere: response.data.map.listaLibere,
        }, () => console.log(this.state.listaLibere.length));
        console.log(response)
      })
      .catch(error => {
        console.log("nessuna risposta dal server");
      });
  }

  getPrenotazioniDonatore = () => {
    ProfiloService.getPrenotazioniDonatore()
      .then(response => {
        console.log(response)
        response.data.list.forEach(
          (x) => {
            const myDate = new Date(x.date);
            x["data"] = myDate.getDate() + "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear();
            x["time"] = myDate.getHours() + ":" + myDate.getMinutes();
            console.log(x)
          }
        )
        this.setState({
          listaPrenotateDonatore: response.data.list,
        });
      })
      .catch(error => {
        console.log("nessuna risposta dal server");
      });
  }

  componentDidMount() {
    this.loadProfilo()
  }


  render() {

    return (
      <div id="accordion" className="mt-2">
        {this.state.aud === "donatore" && this.state.fields.abilitazioneDonazione === 0 &&
          <FormAlert colorType="warning" message="Non sei abilitato a donare" />
        }
        {this.state.aud === "donatore" && this.state.fields.abilitazioneDonazione === 1 &&
          <FormAlert colorType="success" message="Sei abilitato a donare" />
        }
        {this.state.aud === "sedeAvis" && this.state.listaLibere == 0 &&
          <FormAlert colorType="danger" message="Attenzione: Non ci sono date da prenotare" />
        }
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
              {this.state.fields.id && <FormProfilo value={this.state.fields} />}
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
                {this.state.aud === "donatore" && "MODULO"}
                {this.state.aud === "sedeAvis" && "LISTA PRENOTAZIONI"}
                {this.state.aud === "centroTrasfusione" && "LISTA EMERGENZE"}
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
              {this.state.aud === "donatore" && this.state.fields.id && <FormModulo value={this.state.fields.modulo} loadProfilo={this.loadProfilo}/>}
              {this.state.aud === "sedeAvis" && this.state.fields.id && <TableDateLibere data={this.state.listaLibere} />}
              {this.state.aud === "sedeAvis" && this.state.fields.id && <TableDatePrenotate data={this.state.listaPrenotate} />}
              {this.state.aud === "centroTrasfusione" && this.state.fields.id && <TableEmergenzeSangue data={this.state.emergenze} />}
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
              {this.state.aud === "donatore" && this.state.fields.id && <TableDatePrenotateDonatore data={this.state.listaPrenotateDonatore} />}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default ProfiloUtente;
