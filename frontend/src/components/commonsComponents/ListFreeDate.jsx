import React, { PureComponent } from 'react'
import PrenotaService from '../../utils/PrenotaService';
import $ from 'jquery';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css'
import FormModulo from '../FormComponents/FormModulo'
import ProfiloService from "../../utils/ProfiloService"

import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';



const columns = memoize(clickHandler => [
    {
        name: 'ID',
        selector: 'idPrenotazione',
        sortable: true,
    },
    {
        name: 'Comune',
        selector: 'idSedeAvis.comune',
        sortable: true,
    },
    {
        name: 'Giorno',
        selector: 'day',
        sortable: true,
    },
    {
        name: 'Orario',
        selector: 'time',
        sortable: true,
    },
    {
        name: 'Prenota',
        cell: (row) => <button type="button" className="btn btn-success" onClick={clickHandler} id={row.idPrenotazione}>Prenota</button>,
        ignoreRowClick: true,
        allowOverflow: true,
        button: true,
    },
]);

class ListFreeDate extends PureComponent {
    constructor(props) {
        super(props)

        this.state = {

        }
    }

    concludiDonazione = () => {

        PrenotaService.prenota(this.state.prenotazioneDto)
            .then(
                response => {
                    if (response.data) {
                        confirmAlert({
                            message: response.data.message,
                            buttons: [
                                {
                                    label: 'Ok',
                                    onClick: () => window.location.reload()
                                },
                            ],
                            closeOnClickOutside: false,
                        });

                    }
                }
            )
            .catch(
                error => {
                    confirmAlert({
                        message: error.response.data.message,
                        buttons: [
                            {
                                label: 'Ok',
                            },
                        ],
                    });
                }
            )
    }

    modificaModulo = () => {
        {this.props.ruolo === "donatore" &&
            ProfiloService.loadProfilo()
                .then(response => {
                    this.setState({ modulo: response.data.utente.modulo });
                    $("#myModal").modal()
                })
                .catch(error => {
                    console.log("nessuna risposta dal server");
                });
        }
        {this.props.ruolo === "sedeAvis" &&
            ProfiloService.loadModulo(this.state.prenotazioneDto.emailDonatore)
                .then(response => {
                    this.setState({ modulo: undefined }, () => { console.log(this.state) });
                    this.setState({ modulo: response.data.modulo }, () => { console.log(this.state) });
                    $("#myModal").modal()
                })
                .catch(error => {
                    confirmAlert({
                        message: "email non valida!",
                        buttons: [
                            {
                                label: 'Ok',
                            },
                        ],
                    });
                });
        }
    }

    handleButtonClick = (state) => {
        this.setState({
            prenotazioneDto: {
                'idDataLibera': state.target.id,
                'emailDonatore': this.props.emailDonatore
            }
        }, () => {console.log(this.state.prenotazioneDto); console.log(this.props.ruolo)})
   
        confirmAlert({
            title: 'Modifica Modulo',
            message: 'Vuoi modificare il modulo prima di concludere la prenotazione?',
            buttons: [
                {
                    label: 'Modifica Modulo',
                    onClick: () => { this.modificaModulo() }
                },
                {
                    label: 'Concludi la donazione senza modificare il modulo',
                    onClick: () => this.concludiDonazione()
                }
            ],
            closeOnClickOutside: false,
        });
    };

    render() {
        return (
            <div>
                <div className="container">
                    <DataTable
                        title="Date disponibili"
                        columns={columns(this.handleButtonClick)}
                        data={this.props.freeDate}
                        defaultSortField="title"
                    />

                </div>

                <div className="modal" id="myModal">
                    <div className="modal-dialog">
                        <div className="modal-content">

                            <div className="modal-header">
                                <h4 className="modal-title">Modifica modulo</h4>
                                <button type="button" className="close" data-dismiss="modal">&times;</button>
                            </div>

                            <div className="modal-body">
                                {this.state.modulo && <FormModulo value={this.state.modulo} email={this.props.emailDonatore} />}
                            </div>

                            <div className="modal-footer">
                                <button type="button" className="btn btn-success" data-dismiss="modal" onClick={this.concludiDonazione}>Concludi Prenotazione</button>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

        )
    }
}


export default ListFreeDate