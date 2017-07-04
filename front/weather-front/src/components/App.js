import React, { Component } from 'react';
import Search from './Search';
import logo from '../logo.svg';
import './styles/App.css';

class App extends Component {
  render() {
    return (
      <div className="App">

        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Weather App</h2>
        </div>

        <Search />

      </div>
    );
  }
}

export default App;
