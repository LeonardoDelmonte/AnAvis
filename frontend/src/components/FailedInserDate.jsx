import React, { Component } from 'react';



class FailedInserDate extends Component {

    constructor(props) {
        super(props)
    }

    render() {

        return (
            <div>
                {
                this.props.failedDate.map(
                    (x, i) => {
                        var myDate = new Date(x)
                        return (
                        <ul key={i} className="list-group">
                            <li  className="list-group-item list-group-item-danger ">
                                {
                                    "Questa data è già presente : " + myDate.getDate()+ "/" + myDate.getMonth() + 1 + "/" + myDate.getFullYear() + " Alle ore " + myDate.getHours() + ":" + myDate.getMinutes()
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

export default FailedInserDate