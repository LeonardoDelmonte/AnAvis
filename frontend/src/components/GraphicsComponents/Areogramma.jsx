import React, { Component } from 'react';
import CanvasJSReact from './canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class Areogramma extends Component {
    render() {
        const options = {
            animationEnabled: true,
            theme: "light1", // "light1", "dark1", "dark2"
            title: {
                text: "Trip Expenses"
            },
            data: [{
                type: "pie",
                indexLabel: "{label}: {y}%",
                startAngle: -90,
                dataPoints: [
                    { y: 20, label: "Airfare" },
                    { y: 24, label: "Food & Drinks" },
                    { y: 20, label: "Accomodation" },
                    { y: 14, label: "Transportation" },
                    { y: 12, label: "Activities" },
                    { y: 10, label: "Misc" }
                ]
            }]
        }

        return (
            <div>
                <CanvasJSChart options={options}/>
            </div>
        );
    }
}

export default Areogramma;