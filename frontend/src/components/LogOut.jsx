import React, { Component } from 'react';

class LogOut extends Component {

    componentDidMount() {
        localStorage.removeItem("Authorization");
        localStorage.removeItem("nome");
        localStorage.removeItem("cognome");
        this.props.history.push('/Login')
    }

    render() {

        return (
            <div></div>
        );
    }
}

export default LogOut