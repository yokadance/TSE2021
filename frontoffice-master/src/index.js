import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter as Router } from 'react-router-dom'

import './index.css'
import NavBar from './Components/NavBar'

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <NavBar />
    </Router>
  </React.StrictMode>,
  document.getElementById('root'),
)
