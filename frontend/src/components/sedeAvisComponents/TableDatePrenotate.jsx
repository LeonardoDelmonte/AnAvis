import React, { Component } from 'react';
//Components
import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';

//Services
import ProfiloService from "../../utils/ProfiloService";

const columns = memoize(clickHandler => [
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
        name: 'Elimina',
        cell: (row) => <button type="button" className="btn btn-danger" onClick={clickHandler} id={row.idPrenotazione}>Elimina</button>,
        ignoreRowClick: true,
        allowOverflow: true,
        button: true,
    },
]);

class TableDatePrenotate extends Component {

    constructor(props) {
        super(props)
        this.state = {  }
    }
    
    
    handleButtonClick = (state) => {
        console.log(state.target.id)
        ProfiloService.deleteDate(state.target.id)
        .then(
            response => {
               if(response.data){
                   alert("Data Eliminata")
                   window.location.reload();
               }
            }
        )
        .catch(
            error => {
                console.log(error)
                alert("error")//error.response.data.message)
            }
        )
    }
    
    render() {
        return (
            <div>
                <DataTable
                    title="Date Prenotate"
                    columns={columns(this.handleButtonClick)}
                    data={this.props.data}
                    defaultSortField="title"
                    pagination
                />
            </div>
        );
    }
}

export default TableDatePrenotate