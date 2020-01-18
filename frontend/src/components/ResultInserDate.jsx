import React, { Component } from 'react';



class ResultInserDate extends Component {

    render() {
        return (
            <div>
                {this.props.listError && this.props.listOK &&
                    <p>
                    Si sono verificati <b>{this.props.listError.length} </b>errori 
                    <br />
                    Date inserite correttamente: <b>{this.props.listOK.length}</b></p>
                }
                {this.props.listError &&
                    this.props.listError.map(
                        (x, i) => {
                            var myDate = new Date(x)
                            return (
                                <ul key={i} className="list-group m-1">
                                    <li className="list-group-item list-group-item-danger ">
                                        {
                                            "Questa data è già presente : " + myDate.getDate() + "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear() + " Alle ore " + myDate.getHours() + ":" + myDate.getMinutes()
                                        }
                                    </li>
                                </ul>
                            )
                        }
                    )
                }
                {this.props.listOK &&
                    this.props.listOK.map(
                        (x, i) => {
                            var myDate = new Date(x)
                            return (
                                <ul key={i} className="list-group m-1">
                                    <li className="list-group-item list-group-item-success">
                                        {
                                            "Data inserita : " + myDate.getDate() + "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear() + " Alle ore " + myDate.getHours() + ":" + myDate.getMinutes()
                                        }
                                    </li>
                                </ul>
                            )
                        }
                    )
                }
            </div>
        );
    }
}
export default ResultInserDate