
import React, { Component } from 'react';
// import logo from './logo.svg';
import './App.css';
import UserComponent from './components/UserComponent';
import Login from './components/login/login';
import NavBar from './components/utils/navBar.jsx';
import Register from './components/register/register';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import 'antd/dist/antd.css';


class App extends Component {
  render () {
    
    return (
    <div className="App">
        <Router>
        
        <NavBar />
        <Route path="/" component={UserComponent}/>
        <div className="main">

          <Switch>
          <Route path="/login" component={Login} />
          <Route path="/register" component={Register} />
            </Switch>

          </div>
        </Router> 

    </div>
  )
  }
}


export default App;