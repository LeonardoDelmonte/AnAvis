import React, {Component} from 'react';
import PrenotaService from '../utils/PrenotaService';

class ListFreeDate extends Component{
    constructor(props) {
        super(props)
        this.state = {
            freeDate: []
        }
        this.refreshDate = this.refreshDate.bind(this)
        this.prenota = this.prenota.bind(this)
    }

    componentDidMount() {
        this.refreshDate();

    }

    refreshDate() {            
        PrenotaService.search(this.props.sede)
           .then(
               response => {                  
                   this.setState({freeDate:response.data})
               }
           )
    }

    prenota(id){
        PrenotaService.prenota(id)
        .then(
            response=>{
                //boh
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
                                <th>bottone</th>
                            </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.freeDate.map(
                                freeDate =>
                                    <tr key={freeDate.id}>                                      
                                        <td>{freeDate.sedeAvis}</td>
                                        <td>{freeDate.data}</td>
                                        <td><button type="submit" onClick={()=>this.prenota(freeDate.id)}>Prenota!</button></td>
                                    </tr>
                            )
                        }                     
                        </tbody>
                    </table>
                </div>
            </div>
    )
    }
}

export default ListFreeDate