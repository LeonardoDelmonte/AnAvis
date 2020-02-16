import React, { Component } from "react"
//Components
import { compose } from "recompose"
import {
  withScriptjs,
  withGoogleMap,
  GoogleMap,
  Marker,
  InfoWindow
} from "react-google-maps"
import Geocode from "react-geocode";

//Services
import MapsServices from '../../utils/Maps';

Geocode.setLanguage("it");
Geocode.setApiKey("AIzaSyCRfaW0Kx2El3bVuYiB4DYZ5i2w_vsKoDM");

const MapWithAMarker = compose(withScriptjs, withGoogleMap)(props => {

  return (
    <GoogleMap defaultZoom={5} defaultCenter={{ lat: 41.8719, lng: 12.5674 }}>
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
    var points = []

    MapsServices.ottieniComuni()
      .then(response => {
        response.data.entity.map(
          (x, i) => {
            Geocode.fromAddress(x).then(
              response => {
                const { lat, lng } = response.results[0].geometry.location;

                points[i] = {
                  id: i,
                  shelter: x,
                  longitude: lng,
                  latitude: lat
                }
                this.setState({ shelters: points })
              },
              error => {
                console.error(error);
              }
            );
          }
        )
      })
      .catch(error => {
        console.log(error);
      });
  }

  handleClick = (marker) => {
    this.setState({ selectedMarker: marker })
  }

  render() {
    return (
      <div>
        <h1>Tutte le nostre Sedi</h1>
        <MapWithAMarker
          selectedMarker={this.state.selectedMarker}
          markers={this.state.shelters}
          onClick={this.handleClick}
          googleMapURL="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRfaW0Kx2El3bVuYiB4DYZ5i2w_vsKoDM&v=3.exp&libraries=geometry,drawing,places"
          loadingElement={<div style={{ height: `100%` }} />}
          containerElement={<div style={{ height: `400px` }} />}
          mapElement={<div style={{ height: `100%` }} />}
        />
      </div>

    )
  }
}