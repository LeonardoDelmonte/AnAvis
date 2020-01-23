import React, { Component } from 'react';
//Components
import DataTable from 'react-data-table-component';
import memoize from 'memoize-one';

//Services
import CentroTrasfusioneService from "../../utils/CentroTrasfusioneService";

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

class TableEmergenzeSangue extends Component {

    constructor(props) {
        super(props)
        this.state = {  }
    }
    
    
    handleButtonClick = (state) => {
        console.log(state.target.id)
        CentroTrasfusioneService.deleteEmergenze(state.target.id)
        .then(
            response => {
               if(response.data){
                   alert("Emergenza Eliminata")
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
                    title="Emergenze inserite"
                    columns={columns(this.handleButtonClick)}
                    data={this.props.data}
                    defaultSortField="title"
                    pagination
                />
            </div>
        );
    }
}

export default TableEmergenzeSangue