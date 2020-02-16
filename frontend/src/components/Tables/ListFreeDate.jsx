import React, { Component } from 'react';
//Components
import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';
import FormModulo from '../Forms/Modulo'
import { confirmAlert } from 'react-confirm-alert';
//Services
import PrenotaService from '../../utils/PrenotaService';
import ModuloService from "../../utils/ModuloService"
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
        if(isSede()){
            PrenotaService.prenotaPerDonatore(this.state.prenotazioneDto)
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
        if(isDonatore()){
            PrenotaService.prenota(this.state.prenotazioneDto.idPrenotazione)
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
    }

    modificaModulo = () => {
        {isDonatore() &&
            ModuloService.loadModuloDonatore()
                .then(response => {
                    this.setState({ modulo: response.data.entity });
                    $("#myModal").modal()
                })
                .catch(error => {
                    console.log("nessuna risposta dal server");
                });
        }
        {isSede() &&
            ModuloService.loadModuloSede(this.state.prenotazioneDto.email)
                .then(response => {
                    this.setState({ modulo: undefined }, () => { console.log(this.state) });
                    this.setState({ modulo: response.data.entity }, () => { console.log(this.state) });
                    $("#myModal").modal()
                })
                .catch(error => {
                    ShowSimpleAlert("email non valida!")
                });
        }
    }

    handleButtonClick = (state) => {
        if(isSede()){
            this.setState({
            prenotazioneDto: {
                'idPrenotazione': state.target.id,
                'email': this.props.emailDonatore
            }
        }, () => console.log(this.state))
     }
     if(isDonatore()){
            this.setState({
            prenotazioneDto: {
                'idPrenotazione': state.target.id              
            }
        })
     }
        
   
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