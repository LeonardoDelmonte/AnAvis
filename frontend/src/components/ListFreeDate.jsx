import React, { PureComponent } from 'react'
import PrenotaService from '../utils/PrenotaService';
import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';


const columns = memoize(clickHandler => [
    {
        name: 'ID',
        selector: 'idPrenotazione',
        sortable: true,
    },
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
        name: 'Prenota',
        cell: (row) => <button onClick={clickHandler} id={row.idPrenotazione}>Prenota</button>,
        ignoreRowClick: true,
        allowOverflow: true,
        button: true,
    },
]);

class ListFreeDate extends PureComponent {
    constructor(props) {
        super(props)
        this.prenota = this.prenota.bind(this)
    }

    handleButtonClick = (state) => {

        console.log('clicked');
        console.log(state.target.id);
        var prenotazioneDto = {
            'idPrenotazione': state.target.id,
            'idDonatore': 3
        }
        PrenotaService.prenota(prenotazioneDto);
    }


    prenota(id) {
        // PrenotaService.prenota(id)
        //     .then(
        //         response => {
        //             //boh
        //         }
        //     )
        console.log("aaa" + id);
    }

    componentDidMount() {
        console.log(this.props.freeDate)
    }

    render() {
        return (
            <div className="container">
                <DataTable
                    title="Date disponibili"
                    columns={columns(this.handleButtonClick)}
                    data={this.props.freeDate}
                    defaultSortField="title"
                />
            </div>
        )
    }
}


export default ListFreeDate