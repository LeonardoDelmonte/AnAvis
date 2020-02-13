import React, { Component } from 'react';
//Components
import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';
import FormModulo from '../Forms/Modulo'
import { confirmAlert } from 'react-confirm-alert';
//Services
import PrenotaService from '../../utils/PrenotaService';
import ProfiloService from "../../utils/ProfiloService"
//JQuery
import $ from 'jquery';
//Helpers
import {ShowSimpleAlert, isDonatore, isSede} from '../../utils/helpers'


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

class ListFreeDate extends Component {
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
        {isDonatore() &&
            ProfiloService.loadProfilo()
                .then(response => {
                    this.setState({ modulo: response.data.utente.modulo });
                    $("#myModal").modal()
                })
                .catch(error => {
                    console.log("nessuna risposta dal server");
                });
        }
        {isSede() &&
            ProfiloService.loadModulo(this.state.prenotazioneDto.emailDonatore)
                .then(response => {
                    this.setState({ modulo: undefined }, () => { console.log(this.state) });
                    this.setState({ modulo: response.data.modulo }, () => { console.log(this.state) });
                    $("#myModal").modal()
                })
                .catch(error => {
                    ShowSimpleAlert("email non valida!")
                });
        }
    }

    handleButtonClick = (state) => {
        this.setState({
            prenotazioneDto: {
                'idDataLibera': state.target.id,
                'emailDonatore': this.props.emailDonatore
            }
        })
   
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
                        defaultSortField="day"
                        pagination
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