import React, { Component } from "react"
import { compose } from "recompose"
import {
  withScriptjs,
  withGoogleMap,
  GoogleMap,
  Marker,
  InfoWindow
} from "react-google-maps"
import Geocode from "react-geocode";
Geocode.setLanguage("it");
Geocode.setApiKey("AIzaSyCRfaW0Kx2El3bVuYiB4DYZ5i2w_vsKoDM");

const MapWithAMarker = compose(withScriptjs, withGoogleMap)(props => {

  return (
    <GoogleMap defaultZoom={8} defaultCenter={{ lat: 29.5, lng: -95 }}>
      {props.markers.map(marker => {
        const onClick = props.onClick.bind(this, marker)
        return (
          <Marker
            key={marker.id}
            onClick={onClick}
            position={{ lat: marker.latitude, lng: marker.longitude }}
          >
            {props.selectedMarker === marker &&
              <InfoWindow>
                <div>
                  {marker.shelter}
                </div>
              </InfoWindow>}
            }
          </Marker>
        )
      })}
    </GoogleMap>
  )
})

export default class ShelterMap extends Component {
  constructor(props) {
    super(props)
    this.state = {
      shelters: [],
      selectedMarker: false
    }
  }

  componentDidMount() {
    // fetch("https://api.harveyneeds.org/api/v1/shelters?limit=20")
    //   .then(r =>
    //     r.json()
    //   )
    //   .then(data => {
    //     this.setState({ shelters: data.shelters }, () => {
    //       console.log(this.state.shelters)
    //     })
    //     // console.log(data);
    //   })

    // var aaa = []
    // aaa[0] = {  id:"0", 
    //             county:"Italia", 
    //             shelter:"", 
    //             address:"via della riviera", 
    //             city: "Potenza Picena", 
    //             pets:"", 
    //             phone:"", 
    //             accepting:"", 
    //             updated_by:"",
    //             notes:"",
    //             volunteer_needs: "",
    //             longitude: 1,
    //             latitude: 1}


    // this.setState({ shelters: aaa }, () => {
    //   console.log(this.state.shelters)
    // })

    Geocode.fromAddress("Eiffel Tower").then(
      response => {
        const { lat, lng } = response.results[0].geometry.location;
        console.log(lat, lng);
      },
      error => {
        console.error(error);
      }
    );

    // console.log(this.state.shelters)
  }

  handleClick = (marker, event) => {
    // console.log({ marker })
    this.setState({ selectedMarker: marker })
  }

  render() {
    return (
      <MapWithAMarker
        selectedMarker={this.state.selectedMarker}
        markers={this.state.shelters}
        onClick={this.handleClick}
        googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places"
        loadingElement={<div style={{ height: `100%` }} />}
        containerElement={<div style={{ height: `400px` }} />}
        mapElement={<div style={{ height: `100%` }} />}
      />
    )
  }
}