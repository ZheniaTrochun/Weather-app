import React, { Component } from 'react';
import './styles/Weather.css';

class Weather extends Component {
    render() {
        let weatherHolder = null;
        if(this.props.drawable) {
          weatherHolder =
              <div className="card">
                <h1>{this.props.weather.temperatureCelsius} &deg; C</h1>
                <h1>{this.props.weather.windSpeed} MPH</h1>
                <h1>{this.props.weather.generalWeather}</h1>
              </div>
        }

        return (
          <div>
            {weatherHolder}
          </div>
        )
    }
}

export default Weather;
