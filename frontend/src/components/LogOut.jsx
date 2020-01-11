import React, { Component } from 'react';



class LogOut extends Component {

    componentDidMount() {
        localStorage.removeItem("Authorization");
        this.props.history.push('/Login')
    }

    render() {

        return (
            <div><h4>Disconnessione...</h4></div>
        );
    }
}

export default LogOut