
import React, { Component } from 'react';
// import logo from './logo.svg';
import './App.css';
import UserComponent from './components/UserComponent';
import Login from './components/login/login';
import LoginB from './components/login/loginB';
import NavBar from './components/utils/navBar.jsx';
import SelectUser from './components/utils/selectUser.jsx';
import SelectReg from './components/utils/selectReg.jsx';
import Register from './components/register/register';
import RegisterB from './components/register/registerB';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import 'antd/dist/antd.css';


class App extends Component {
  render () {
    return (
    <div className="App">
        <Router>
        <NavBar />
        {/* <UserComponent /> */}
        <div className="main">

          <Switch>
          <Route path="/"exact={true} component={UserComponent} />
          <Route path="/login" exact={true}component={SelectUser} />
          <Route path="/login/user" exact={true}component={Login} />
          <Route path="/login/business" exact={true}component={LoginB} />
          <Route path="/register" exact={true}component={SelectReg} />
          <Route path="/register/user" exact={true}component={Register} />
          <Route path="/register/business" exact={true}component={RegisterB} />
          </Switch>

          </div>
        </Router> 

    </div>
  )
  }
}


export default App;