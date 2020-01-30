import React, { Component } from 'react';
//Components
import Areogramma from '../GraphicsComponents/Areogramma';
import Cartesiano from '../GraphicsComponents/Cartesiano';
import Istogramma from '../GraphicsComponents/Istogramma';
import graphicsService from "../../utils/graphicsService";


class Home extends Component {

    constructor(props) {
        super(props)
        this.state = {}
    }

    GetDatiEmergenzeSangue = () => {
        var dataPoints = []
        graphicsService.GetDatiEmergenzeSangue()
            .then(response => {
                response.data.list.forEach(
                    (x) => {
                       dataPoints.push({label : x[1], y: x[0]})
                    }
                )
                this.setState({
                    dataPoints: dataPoints
                }, () => console.log(this.state))
            })
            .catch(error => {
                console.log("nessuna risposta dal server");
            });
    }

    componentDidMount() {
        this.GetDatiEmergenzeSangue()
    }


    render() {
        return (
            <div>
                <h1>Benvenuto</h1>
                {/* <Maps /> */}
                <Istogramma dataPoints={this.state.dataPoints} />
                {/* <Areogramma />
                <Cartesiano /> */}
            </div>
        );
    }
}

export default Home