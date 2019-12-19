import React, { Component } from 'react';
import PrenotaService from '../utils/PrenotaService';

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

    render() {
        return (
            <div className="container">
                <h3>Date Disponibili</h3>
                <div className="container">
                    {<table className="table">
                        <thead>
                            <tr>
                                <th>sede</th>
                                <th>data</th>
                                <th>bottone</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.props.freeDate.map(
                                    v =>
                                        <tr key={v.idPrenotazione}>
                                            <td>{v.idSedeAvis.comune}</td>
                                            <td>{v.date}</td>
                                            <td><button type="submit" onClick={() => this.prenota(v.id)}>Prenota!</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>}
                </div>
            </div>
        )
    }
}

export default ListFreeDate