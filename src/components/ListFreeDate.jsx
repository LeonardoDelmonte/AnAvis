import React, {Component} from 'react';
import PrenotaService from '../utils/PrenotaService';

class ListFreeDate extends Component{
    constructor(props) {
        super(props)
        this.state = {
            freeDate: []
        }
        this.refreshDate = this.refreshDate.bind(this)
    }

    componentDidMount() {
        this.refreshDate();
    }

    refreshDate() {         
        PrenotaService.search()//HARDCODED
           .then(
               response => {
                   console.log(response);
                   //this.setState({freeDate:response.data})
               }
           )
    }

    render(){
        return(
            <div className="container">
                <h3>All FreeDate</h3>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>sede</th>
                                <th>data</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>porcodio</td>
                            <td>porcodio</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
    )
    }
}

export default ListFreeDate