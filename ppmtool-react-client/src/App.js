import React, { Component } from 'react'
import './App.css'
import Dashboard from './components/Dashboard'
import Header from './components/layout/Header'
import 'bootstrap/dist/css/bootstrap.min.css'

class App extends Component {
  render () {
    return (
      <div className='App'>
        <Header />
        <Dashboard />
      </div>
    )
  }
}

export default App
