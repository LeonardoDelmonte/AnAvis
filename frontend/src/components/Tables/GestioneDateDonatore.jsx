import React, { Component } from 'react';
//Components
import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';
//Services
import GestioneDonatore from "../../utils/GestioneDonatore";
//-----Helpers
import { dateToString, timeToString, ShowSimpleAlert} from '../../utils/helpers'

const columns = memoize(clickHandler => [
    {
        name: 'ID',
        selector: 'idPrenotazione',
        sortable: true,
    },
    {
        name: 'Sede',
        selector: 'idSedeAvis.comune',
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

class GestioneDateDonatore extends Component {

    constructor(props) {
        super(props)
        this.state = {}
    }

    getPrenotazioni = () => {
        GestioneDonatore.ottieniDate()
            .then(response => {
                console.log(response)
                response.data.entity.forEach(
                    (x) => {
                        const myDate = new Date(x.date);
                        x["data"] = dateToString(myDate)
                        x["time"] = timeToString(myDate)
                        console.log(x)
                    }
                )
                this.setState({
                    listaPrenotate: response.data.entity,
                });
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
    }

    handleButtonClick = (state) => {
        GestioneDonatore.eliminaPrenotazione(state.target.id)
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
                    ShowSimpleAlert("error")//error.response.data.message)
                }
            )
    }

    componentDidMount() {
        this.getPrenotazioni()
    }

    render() {
        return (
            <div>
                <DataTable
                    title="Date Prenotate"
                    columns={columns(this.handleButtonClick)}
                    data={this.state.listaPrenotate}
                    defaultSortField="title"
                    pagination
                />
            </div>
        );
    }
}

export default GestioneDateDonatore