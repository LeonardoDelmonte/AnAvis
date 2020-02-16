import React from 'react';

function ResultInserDate(props) {
    return (
        <div>
        {console.log(props)}
            {props.listError && props.listOK &&
                <p>
                Si sono verificati <b>{props.listError.length} </b>errori 
                <br />
                Date inserite correttamente: <b>{props.listOK.length}</b></p>
            }
            {props.listError &&
                props.listError.map(
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
            {props.listOK &&
                props.listOK.map(
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
export default ResultInserDate