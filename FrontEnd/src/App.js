import React from 'react';
// import logo from './logo.svg';
import './App.css';
 import UserComponent from './components/UserComponent';
import Login from './components/login/login';
import 'antd/dist/antd.css';


function App() {
  return (
    <div className="App">
        {/* <UserComponent /> */}
        <Login />
    </div>
  );
}

export default App;