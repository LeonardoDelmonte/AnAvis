import React, { Component } from 'react';
//Components
import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';
//Services
import GestioneSedeAvis from "../../utils/GestioneSedeAvis";
//-----Helpers
import { dateToString, timeToString, ShowSimpleAlert, isSede } from '../../utils/helpers'

const columnsPrenotate = memoize(clickHandler => [
    {
        name: 'ID',
        selector: 'idPrenotazione',
        sortable: true,
    },
    {
        name: 'Email Donatore',
        selector: 'idDonatore.email',
        sortable: true,
    },
    {
        name: 'Data',
        selector: 'data',
        sortable: true,
    },
    {
        name: 'Ora',
        selector: 'time',
        sortable: true,
    },
    {
        name: 'Elimina',
        cell: (row) => <button type="button" className="btn btn-danger" onClick={clickHandler} id={row.idPrenotazione}>Elimina</button>,
        ignoreRowClick: true,
        allowOverflow: true,
        button: true,
    },
]);

const columnsLibere = memoize(clickHandler => [
    {
        name: 'ID',
        selector: 'idPrenotazione',
        sortable: true,
    },
    {
        name: 'Data',
        selector: 'data',
        sortable: true,
    },
    {
        name: 'Ora',
        selector: 'time',
        sortable: true,
    },
    {
        name: 'Elimina',
        cell: (row) => <button type="button" className="btn btn-danger" onClick={clickHandler} id={row.idPrenotazione}>Elimina</button>,
        ignoreRowClick: true,
        allowOverflow: true,
        button: true,
    },
]);

class GestioneDateSede extends Component {

    constructor(props) {
        super(props)
        this.state = {}
    }

    getPrenotazioni = () => {
        GestioneSedeAvis.getPrenotazioni()
            .then(response => {
                console.log(response)
                response.data.entity.listaLibere.forEach(
                    (x) => {
                        const myDate = new Date(x.date);
                        x["data"] = dateToString(myDate)
                        x["time"] = timeToString(myDate)
                    }
                )
                response.data.entity.listaPrenotate.forEach(
                    (x) => {
                        const myDate = new Date(x.date);
                        x["data"] = dateToString(myDate)
                        x["time"] = timeToString(myDate)
                    }
                )
                this.setState({
                    listaPrenotate: response.data.entity.listaPrenotate,
                    listaLibere: response.data.entity.listaLibere,
                });
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
    }

    handleButtonClickLibere = (state) => {
        console.log(state)
        GestioneSedeAvis.deleteDate(state.target.id)
            .then(
                response => {
                    if (response.data) {
                        ShowSimpleAlert("Data Eliminata")
                        this.getPrenotazioni()
                    }
                }
            )
            .catch(
                error => {
                    console.log(error)
                    ShowSimpleAlert("error")
                }
            )
    }

    handleButtonClickPrenotate = (state) => {
        console.log(state.target.id)
        GestioneSedeAvis.deleteDate(state.target.id)
            .then(
                response => {
                    if (response.data) {
                        ShowSimpleAlert("Data Eliminata")
                        this.getPrenotazioni()
                    }
                }
            )
            .catch(
                error => {
                    console.log(error)
                    ShowSimpleAlert("error")
                }
            )
    }

    componentDidMount() {
        this.getPrenotazioni();
    }

    render() {
        return (
            <div>
                {isSede() && this.state.listaLibere === 0 &&
                    <div class="alert alert-danger">
                        <strong>Info!</strong> Attenzione: Non ci sono date da prenotare.
                    </div>
                }
                <DataTable
                    title="Date Prenotate"
                    columns={columnsPrenotate(this.handleButtonClickPrenotate)}
                    data={this.state.listaPrenotate}
                    defaultSortField="title"
                    pagination
                />

                <DataTable
                    title="Date Libere"
                    columns={columnsLibere(this.handleButtonClickLibere)}
                    data={this.state.listaLibere}
                    defaultSortField="title"
                    pagination
                />
            </div>
        );
    }
}

export default GestioneDateSede