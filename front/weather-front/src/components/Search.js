import React, { Component } from 'react';
import Weather from './Weather';
import axios from 'axios';
import './styles/Search.css';

class Search extends Component {
  constructor(props) {
    super(props);

    this.state = {value: '',
        weather: {temperatureCelsius: '', windSpeed: '', windDeg: '', generalWeather: ''},
        drawable: false};

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value,
        weather: this.state.weather,
        drawable: this.state.drawable});
  }

  handleSubmit(event) {
    axios.get('http://localhost:8080/getWeather?city=' + this.state.value)
        .then(response => {
          console.log(response);
          this.setState({weather: response.data, drawable: true});
        }).catch(err => {
          this.setState({drawable: false});
        });

    event.preventDefault();
  }
  render() {

    return (
      <div className="Search">
        <form className="form" onSubmit={this.handleSubmit}>

          <div className="group">
            <input type="text" value={this.state.value} onChange={this.handleChange} />
            <span className="highlight"></span>
            <span className="bar"></span>
            <label>City</label>
          </div>

          <input type="submit" className="submit-btn" value="Search" />
        </form>

        <Weather weather={this.state.weather} drawable={this.state.drawable} />
      </div>
    );
  }
}

export default Search;
