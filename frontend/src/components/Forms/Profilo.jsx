import React, { Component } from 'react';
//Components
import Input from '../FormComponents/Input'
import Button from '../FormComponents/Button'
//Services
import ProfiloService from '../../utils/ProfiloService';
//Helpers
import { ShowSimpleAlert, isDonatore} from '../../utils/helpers'

class Profilo extends Component {

    constructor(props) {
        super(props)
        this.state = {
            fields: {
            },
        }
    }

    handleChange = event => {
        const value = event.target.value;
        const name = event.target.name;

        this.setState(prevState => ({
            fields: {
                ...prevState.fields,
                [name]: value
            }
        }));
    }

    handleSubmit = e => {
        e.preventDefault();
        var utente = { [this.state.fields.ruolo]: this.state.fields };
        ProfiloService.updateProfilo(utente)
            .then(response => {
                ShowSimpleAlert(response.data.message)
            })
            .catch(error => {
                ShowSimpleAlert(error.response.data.message)
            });
    };

    componentDidMount() {
        ProfiloService.loadProfilo()
            .then(response => {
                this.setState({ fields: response.data.utente });
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
    }

    render() {

        return (
            <form onSubmit={this.handleSubmit} id="ProfiloForm">
                <h3>DATI BASE</h3>
                {isDonatore() && this.state.fields.abilitazioneDonazione === 0 &&
                    <div className="alert alert-info">
                        <strong>Info!</strong>Non sei abilitato a donare
                    </div>
                }
                {isDonatore() && this.state.fields.abilitazioneDonazione === 1 &&
                    <div className="alert alert-info">
                        <strong>Info!</strong> Sei abilitato a donare
                    </div>
                }
                {this.state.fields &&
                    < Input label="Email" type="text" id="email" name="email" value={this.state.fields.email} onChange={this.handleChange} required />
                }
                {/*campi solo donatore */}
                {this.state.fields && this.state.fields.ruolo === "donatore" &&
                    <div>
                        <Input label="Nome" type="text" id="nome" name="nome" value={this.state.fields.nome} onChange={this.handleChange} required />
                        <Input label="Cognome" type="text" id="cognome" name="cognome" value={this.state.fields.cognome} onChange={this.handleChange} required />
                    </div>
                }
                {/*campi SedeAvis e centroTrasfusione */}
                {this.state.fields && (this.state.fields.ruolo === "sedeAvis" || this.state.fields.ruolo === "centroTrasfusione") &&
                    <div>
                        <Input label="Regione" type="text" id="regione" name="regione" value={this.state.fields.regione} onChange={this.handleChange} required />
                        <Input label="Provincia" type="text" id="provincia" name="provincia" value={this.state.fields.provincia} onChange={this.handleChange} required />
                        <Input label="Comune" type="text" id="comune" name="comune" value={this.state.fields.comune} onChange={this.handleChange} required />
                        <Input label="Indirizzo" type="text" id="indirizzo" name="indirizzo" value={this.state.fields.indirizzo} onChange={this.handleChange} required />
                    </div>
                }
                {/*campi SedeAvis */}
                {this.state.fields && this.state.fields.ruolo === "sedeAvis" &&
                    <div>
                        <Input label="Telefono" type="text" id="telefono" name="telefono" value={this.state.fields.telefono} onChange={this.handleChange} required />
                    </div>
                }
                {/*campi centroTrasfusione*/}
                {this.state.fields && this.state.fields.ruolo === "centroTrasfusione" &&
                    <div>
                        <Input label="Direttore" type="text" id="direttore" name="direttore" value={this.state.fields.direttore} onChange={this.handleChange} required />
                        <Input label="Ospedale" type="text" id="ospedale" name="ospedale" value={this.state.fields.ospedale} onChange={this.handleChange} required />
                    </div>
                }
                <Button type="submit" value="Modifica" colorType="primary" />
            </form>
        );
    }
}

export default Profilo