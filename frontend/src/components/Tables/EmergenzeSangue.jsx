import React, { Component } from 'react';
//Components
import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';
import { ShowSimpleAlert } from '../../utils/helpers'
//Services
import GestioneCentroTrasfusione from "../../utils/GestioneCentroTrasfusione";
//-----Helpers
import {dateToString} from '../../utils/helpers'

const columns = memoize(clickHandler => [
    {
        name: 'ID',
        selector: 'id',
        sortable: true,
    },
    {
        name: 'Gruppo Sanguigno',
        selector: 'gruppoSanguigno',
        sortable: true,
    },
    {
        name: 'Data Emergenza',
        selector: 'dataEmergenza',
        sortable: true,
    },
    {
        name: 'Elimina',
        cell: (row) => <button type="button" className="btn btn-danger" onClick={clickHandler} id={row.id}>Elimina</button>,
        ignoreRowClick: true,
        allowOverflow: true,
        button: true,
    },
]);

class EmergenzeSangue extends Component {

    constructor(props) {
        super(props)
        this.state = {}
    }

    getEmergency = () =>{
        GestioneCentroTrasfusione.getEmergency()
            .then(response => {
                response.data.entity.forEach(
                    (x) => {
                        const myDate = new Date(x.dataEmergenza);
                        x["dataEmergenza"] = dateToString(myDate)
                    }
                )
                this.setState({
                    emergenze: response.data.entity
                });
            })
    }

    handleButtonClick = (state) => {
        console.log(state.target.id)
        GestioneCentroTrasfusione.deleteEmergenze(state.target.id)
            .then(
                response => {
                    if (response.data) {
                        ShowSimpleAlert("Emergenza Eliminata")
                        this.getEmergency();
                    }
                }
            )
            .catch(
                error => {
                    console.log(error)
                    alert("error")
                }
            )
    }

    componentDidMount() {
        this.getEmergency();
    }

    render() {
        return (
            <div>
                <DataTable
                    title="Emergenze inserite"
                    columns={columns(this.handleButtonClick)}
                    data={this.state.emergenze}
                    defaultSortField="title"
                    pagination
                />
            </div>
        );
    }
}

export default EmergenzeSangue