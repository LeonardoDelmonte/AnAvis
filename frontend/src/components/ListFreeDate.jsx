import React, { Component } from 'react';
import PrenotaService from '../utils/PrenotaService';
import DataTable from 'react-data-table-component';

const columns = [
    {
        name: 'Denominazione',
        selector: 'idSedeAvis.denominazione',
        sortable: true,
    },
    {
        name: 'Regione',
        selector: 'idSedeAvis.regione',
        sortable: true,
    },
    {
        name: 'Provincia',
        selector: 'idSedeAvis.provincia',
        sortable: true,
    },
    {
        name: 'Comune',
        selector: 'idSedeAvis.comune',
        sortable: true,
    },
    {
        name: 'date',
        selector: 'date',
        sortable: true,
    },
    {
        name: 'Year',
        selector: 'year',
        sortable: true,
    },
];

class ListFreeDate extends Component {
    constructor(props) {
        super(props)

        this.prenota = this.prenota.bind(this)
    }

    prenota(id) {
        PrenotaService.prenota(id)
            .then(
                response => {
                    //boh
                }
            )
    }

    componentDidMount(){
        console.log(this.props.freeDate)
    }

    render() {
        return (
            <div className="container">
                <DataTable
                    title="Date disponibili"
                    columns={columns}
                    data={this.props.freeDate}
                    defaultSortField="title"
                />
            </div>
        )
    }
}

export default ListFreeDate