import React, { Component } from 'react';
//Services
import graphicsService from "../../utils/GestioneCentroTrasfusione";
//canvas
import CanvasJSReact from '../../utils/cancas/canvasjs.react';


var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class Istogramma extends Component {

	constructor(props) {
        super(props)
        this.state = {}
	}
	
	componentDidMount() {
		var dataPoints = []
		graphicsService.GetCountRichieste()
			.then(response => {
				response.data.entity.forEach(
					(x) => {
						dataPoints.push({ label: x[1], y: x[0] })
					}
				)
				this.setState({
					dataPoints: dataPoints
				})
			})
			.catch(error => {
				console.log("nessuna risposta dal server");
			});
	}

	render() {

		const options = {
			title: {
				text: "Richieste emergenze sangue"
			},
			animationEnabled: true,
			data: [
				{
					type: "column",
					dataPoints: this.state.dataPoints
				}
			]
		}

		return (
			<div>
				<CanvasJSChart options={options} />
			</div>
		);
	}
}

export default Istogramma;