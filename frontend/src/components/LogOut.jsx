import React, { Component } from 'react';

class LogOut extends Component {

    componentDidMount() {
        localStorage.removeItem("Authorization");
        this.props.history.push('/Login')
    }

    render() {

        return (
            <div></div>
        );
    }
}

export default LogOut