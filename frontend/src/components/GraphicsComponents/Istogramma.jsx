import React, { Component } from 'react';
import CanvasJSReact from './canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;
 
class Istogramma extends Component {
		render() {
		const options = {
			title: {
				text: "Richieste emergenze sangue"
			},
			animationEnabled: true,
			data: [
			{
				type: "column",
				dataPoints: this.props.dataPoints
			}
			]
		}
		
		return (
		<div>
			<CanvasJSChart options = {options} />
		</div>
		);
	}
}

export default Istogramma;